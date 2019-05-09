package com.wyl.webdemo.serviceImpl.local;

import com.wyl.webdemo.annotation.AnalysisActuator;
import com.wyl.webdemo.common.enums.OperationKinds;
import com.wyl.webdemo.dto.StudentInfoDTO;
import com.wyl.webdemo.entity.local.StudentInfo;
import com.wyl.webdemo.mapper.local.StudentInfoMapper;
import com.wyl.webdemo.service.local.StudentInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyl123
 * @since 2018-10-08
 */
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements StudentInfoService {

    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Override
    public StudentInfo getStudentById(Integer id, Long orgId, Long cascadeId) {
//        int i=1/0;
        return studentInfoMapper.selectById(id);
    }

    @Override
    public Integer deleteStudentById(Integer id, Long orgId, Long cascadeId, Integer activeFlag) {
//        int i=1/0;
        StudentInfo studentInfo = studentInfoMapper.selectById(id);
        studentInfo.setIsDel(activeFlag);
        studentInfoMapper.updateById(studentInfo);
        return id;
    }

    @Override
    public Integer addStudentById(StudentInfo studentInfo, Long orgId, Long cascadeId) {
//        int i=1/0;
        studentInfoMapper.insert(studentInfo);
        return studentInfo.getId();
    }


    @Override
    public Integer editStudentById(Long orgId, Long cascadeId, StudentInfoDTO studentInfoDTO) {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setIsDel(0);
        studentInfo.setId(studentInfoDTO.getNum());
        studentInfo.setName(studentInfoDTO.getName());
        studentInfo.setAge(studentInfoDTO.getAge());
        studentInfo.setNumber(studentInfoDTO.getNumber());
        studentInfo.setPassWord(studentInfoDTO.getPassWord());

        studentInfoMapper.updateById(studentInfo);
        return studentInfo.getId();
    }


}
