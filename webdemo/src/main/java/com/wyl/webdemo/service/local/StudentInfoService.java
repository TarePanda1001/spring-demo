package com.wyl.webdemo.service.local;

import com.wyl.webdemo.dto.StudentInfoDTO;
import com.wyl.webdemo.entity.local.StudentInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyl123
 * @since 2018-10-08
 */
public interface StudentInfoService extends IService<StudentInfo> {

    StudentInfo getStudentById(Integer id, Long orgId, Long cascadeId);

    Integer deleteStudentById(Integer id, Long orgId, Long cascadeId, Integer activeFlag);

    Integer addStudentById(StudentInfo studentInfo, Long orgId, Long cascadeId);

    Integer editStudentById(Long orgId, Long cascadeId, StudentInfoDTO studentInfoDTO);

}
