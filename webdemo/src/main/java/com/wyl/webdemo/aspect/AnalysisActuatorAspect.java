package com.wyl.webdemo.aspect;

import com.alibaba.fastjson.JSON;
import com.wyl.webdemo.annotation.AnalysisActuator;
import com.wyl.webdemo.common.enums.ModuleEnums;
import com.wyl.webdemo.common.enums.OperationKinds;
import com.wyl.webdemo.common.enums.OperationStatus;
import com.wyl.webdemo.entity.local.OpLog;
import com.wyl.webdemo.entity.local.OperationRecord;
import com.wyl.webdemo.service.local.OperationRecordService;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.wyl.webdemo.common.constant.RabbitmqQueues.DIRECT_QUEUE;
import static com.wyl.webdemo.common.utils.MapUtils.map2String;

/**
 * 定义切面，定义注解analysisActuator
 */
@Aspect
@Component
@Slf4j
public class AnalysisActuatorAspect {

    private static final String REDIS_KEY_PREFIX = "OPER_LOG";

    @Resource
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(analysisActuator)")
    public void serviceStatistics(AnalysisActuator analysisActuator){
    }

    @Before("serviceStatistics(analysisActuator)")
    public void doBefore(JoinPoint joinPoint, AnalysisActuator analysisActuator) throws NotFoundException, ClassNotFoundException {
        // 记录请求到达时间
        Date reqTime = new Date();
        log.info("access time : {} , type : {}", reqTime, analysisActuator.oper());
        // 获取ip
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        //获取方法名称
        String methodName = joinPoint.getSignature().getName();
        //参数
        Object[] args = joinPoint.getArgs();
        //获取被切参数名称及参数值
        Map<String, Object> nameAndArgs = getFieldsName(this.getClass(), clazzName, methodName, args);
        OpLog opLog = new OpLog();
        opLog.setOrgId(158L);
        opLog.setCampusId(3794L);
        opLog.setStaffId(89L);
        opLog.setModule(analysisActuator.module().getModule());
        opLog.setOper(analysisActuator.oper().getCode());
        opLog.setParams(map2String(nameAndArgs));
        opLog.setCreateTime(new Date());
        opLog.setTableName(analysisActuator.module().getTableName());
        opLog.setIp(request.getRemoteAddr());
        opLog.setActionName(request.getRequestURI());
        if(StringUtils.isNotEmpty(analysisActuator.opDetail())) {
            opLog.setDescription(analysisActuator.opDetail());
        }else {
            if(analysisActuator.oper().getCode().equals(OperationKinds.UPDATE_STUTAS.getCode())){
                // 记录停用、启用状态
                Integer activeFlag = 0;
                Map paramMap = new HashMap();
                try {
                    paramMap = JSON.parseObject(opLog.getParams());
                    if(paramMap.containsKey("activeFlag")){
                        activeFlag = Integer.parseInt(paramMap.get("activeFlag").toString());
                    }
                }catch (NumberFormatException e){
                    log.info("[NumberFormatException] parseObject(activeFlag) error : " + paramMap.get("activeFlag").toString());
                }
                catch (Exception e){
                    log.info("[JSON] parseObject(params) error : " + opLog.getParams());
                }
                opLog.setDescription(activeFlag.equals(0) ? "停用" + analysisActuator.module().getModuleName() : "启用" + analysisActuator.module().getModuleName());
            }else {
                opLog.setDescription(analysisActuator.oper().getDesc() + analysisActuator.module().getModuleName());
            }
        }
        try {
            this.redisTemplate.opsForValue().set(getRedisKey(opLog.getOrgId(), opLog.getCampusId(), opLog.getStaffId()), opLog);
        }catch (Exception e){
            log.info("Exception = {}", e);
        }
    }

    @AfterReturning(value = "serviceStatistics(analysisActuator)", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result, AnalysisActuator analysisActuator){
        Date resTime = new Date();
        log.info("result : {}", result);
        if(result != null) {
            try {
                OpLog opLog = (OpLog) redisTemplate.opsForValue().get(getRedisKey(158L, 3794L, 89L));
                if(opLog == null) {
                    return;
                }
                Map resultMap = JSON.parseObject(JSON.toJSONString(result));
                opLog.setStatus(resultMap.get("code") == null || !"0".equals(resultMap.get("code").toString()) ? 0 : 1);
                opLog.setResult(JSON.toJSONString(result));
                opLog.setTableId(Long.parseLong(resultMap.get("data").toString()));
                Long costTime = resTime.getTime() - opLog.getCreateTime().getTime();
                opLog.setTime(costTime.intValue());
                this.redisTemplate.opsForList().rightPush("operation_records", opLog);
                this.redisTemplate.delete(getRedisKey(158L, 3794L, 89L));
            }catch (Exception e){
                log.info("[JSON] parseObject(result) error : {}", result);
            }
        }
    }

    @AfterThrowing(value = "serviceStatistics(analysisActuator)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e, AnalysisActuator analysisActuator) {
        Date resTime = new Date();
        OpLog opLog = (OpLog) redisTemplate.opsForValue().get(getRedisKey(158L, 3794L, 89L));
        if(null == opLog) {
            return;
        }
        opLog.setStatus(0);
        Long costTime = resTime.getTime() - opLog.getCreateTime().getTime();
        opLog.setTime(costTime.intValue());
        opLog.setRemarks(e.getMessage());
        this.redisTemplate.opsForList().rightPush("operation_records", opLog);
        this.redisTemplate.delete(getRedisKey(158L, 3794L, 89L));
    }
    /**
     * 通过反射机制 获取被切参数名以及参数值
     *
     * @param cls
     * @param clazzName
     * @param methodName
     * @param args
     * @return
     * @throws NotFoundException
     */
    private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException {
        Map<String, Object> map = new HashMap<String, Object>();

        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
        }
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            //paramNames即参数名
            map.put(attr.variableName(i + pos), args[i]);
        }
        return map;
    }

    private String getRedisKey(Long orgId, Long campusId, Long staffId){
        return REDIS_KEY_PREFIX + "_" + orgId + "_" + campusId + "_" + staffId;
    }

}
