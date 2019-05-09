package com.wyl.webdemo.serviceImpl.local;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPathException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.EnumUtils;
import com.wyl.webdemo.annotation.FieldProp;
import com.wyl.webdemo.common.enums.ModuleAttributesEnums;
import com.wyl.webdemo.common.enums.OperationKinds;
import com.wyl.webdemo.dto.OpDTO;
import com.wyl.webdemo.dto.OpLogVO;
import com.wyl.webdemo.entity.local.OpLog;
import com.wyl.webdemo.entity.local.StudentInfo;
import com.wyl.webdemo.mapper.local.OpLogMapper;
import com.wyl.webdemo.service.local.OpLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyl123
 * @since 2018-12-17
 */
@Service
public class OpLogServiceImpl extends ServiceImpl<OpLogMapper, OpLog> implements OpLogService {

    @Override
    public List<OpLogVO> getOpList(Long orgId, Long campusId, OpDTO dto){
        // 为了分页，只能先按时间排序 获取分页内容，在筛选。
        Page<OpLog> logList = this.selectPage(new Page(1, 20),
                new EntityWrapper<OpLog>()
                        .eq("org_id", 158L)
                        .eq("campus_id", 3794L)
                        .eq("del_flag", 0)
                        .eq("status", 1)
                        .eq("module", dto.getModule())
                        .eq("table_id", dto.getModuleId())
                        .eq("staff_id", dto.getStaffId())
                        .orderBy("create_time", true)
        );
        Class<?> clazz = StudentInfo.class;
        // 获取保存编辑操作，按时间先后顺序排序
        List<OpLog> editlogList = new ArrayList<>();
        List<OpLog> restLogList = new ArrayList<>();
        for(OpLog opLog : logList.getRecords()){
            if(opLog.getOper().equals(OperationKinds.ADD.getCode()) || opLog.getOper().equals(OperationKinds.EDIT.getCode())){
                editlogList.add(opLog);
            }else {
                restLogList.add(opLog);
            }
        }
        if(CollectionUtils.isEmpty(editlogList)){
            return Collections.emptyList();
        }
        List<OpLogVO> logVOList = new ArrayList<>();
        for(int i = 0 ; i < editlogList.size() ; i++){
            OpLog opLog = editlogList.get(i);
            OpLogVO opLogVO = new OpLogVO();
            if(i == 0){
                opLogVO.setStaffName(opLog.getStaffId().toString());
                opLogVO.setDescription(opLog.getDescription());
                opLogVO.setCreateTime(opLog.getCreateTime());
                logVOList.add(opLogVO);
                continue;
            }
            buildOpLogVO(opLogVO, editlogList.get(i - 1), opLog, clazz);
            logVOList.add(opLogVO);
        }

        for (OpLog opLog : restLogList){
            OpLogVO opLogVO = new OpLogVO();
            opLogVO.setStaffName(opLog.getStaffId().toString());
            opLogVO.setDescription(opLog.getDescription());
            opLogVO.setCreateTime(opLog.getCreateTime());
            logVOList.add(opLogVO);
        }
        Collections.sort(logVOList, new Comparator<OpLogVO>() {
            @Override
            public int compare(OpLogVO o1, OpLogVO o2) {
                return o1.getCreateTime().before(o2.getCreateTime()) ? -1 : 1;
            }
        });
        return logVOList;
    }

    /**
     * 组装操作信息
     * @param opLogVO
     * @param preAction 上一个操作
     * @param action 当前操作
     */
    private void buildOpLogVO(OpLogVO opLogVO, OpLog preAction, OpLog action, Class<?> clazz){
        opLogVO.setStaffName(action.getStaffId().toString());
        opLogVO.setCreateTime(action.getCreateTime());
        try {
            // name -> FieldProp
            Map<String, FieldProp> fieldPropMap = new HashMap<>();
            // value -> Enum.desc
            Map<String, String> descMap = new HashMap<>();
            Field[] fields = clazz.getDeclaredFields();
            for (int index = 0; index < fields.length; index++) {
                FieldProp anno = fields[index].getAnnotation(FieldProp.class);
                if(anno == null){
                    continue;
                }
                if(!fieldPropMap.keySet().contains(anno.name())) {
                    fieldPropMap.put(anno.name(), anno);
                    System.out.println(anno.name() + "    " + anno.label() + "    i:" + anno.ignore() + "   d:" + anno.needDetail());
                    if (anno.needChange()) {
                        Class<Enum> enumClazz = (Class<Enum>) Class.forName(anno.targetEnum().getName());
                        Enum[] enumConstants = enumClazz.getEnumConstants();
                        Method getDesc = enumClazz.getMethod("getDesc");
                        Method getValue = enumClazz.getMethod("getValue");
                        for (Enum enum1 : enumConstants) {
                            //得到枚举实例名
                            String name2 = enum1.name();
                            //执行枚举方法获得枚举实例对应的值
                            Object value = getValue.invoke(enum1);
                            Object desc = getDesc.invoke(enum1);
                            if(!descMap.keySet().contains(value.toString())){
                                descMap.put(value.toString(), desc.toString());
                            }
                            System.out.println(name2 + "  value:" + value.toString() + " desc:" + desc.toString());
                        }
                    }
                }
            }
            Map<String, Object> actionMap = JSON.parseObject(action.getParams());
            Map<String, Object> preActionMap = JSON.parseObject(preAction.getParams());
            StringBuilder content = new StringBuilder();
            for(String argName : actionMap.keySet()){
                if(!preActionMap.keySet().contains(argName)){
                    continue;
                }
                if(!actionMap.get(argName).equals(preActionMap.get(argName))){
                    // todo 具体一些模块的一些转化，例如校区范围0-1转换等
                    String label = "";
                    String before = "";
                    String after = "";
                    FieldProp curProp = fieldPropMap.get(argName);
                    if(curProp == null){
                        continue;
                    }
                    label = curProp.label();
                    if(curProp.ignore()) {
                        continue;
                    }
                    if(curProp.needChange()){
                        before = descMap.get(preActionMap.get(argName).toString());
                        after = descMap.get(actionMap.get(argName).toString());
                    }else {
                        before = preActionMap.get(argName).toString();
                        after = actionMap.get(argName).toString();
                    }
                    buildContent(content, label, before, after, curProp.needDetail());
                }
            }
            opLogVO.setDescription(content.toString());
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        }
        catch (InvocationTargetException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
            opLogVO.setDescription(StringUtil.EMPTY_STRING);
        }
    }

    private void buildContent(StringBuilder content, String label, String before, String after, boolean needDetail){
        if(needDetail){
            content.append("编辑" + label + "：")
                    .append(before + " 变为 " + after)
                    .append("<br/>");
        }else {
            content.append("修改了" + label)
                    .append("<br/>");
        }
    }

}
