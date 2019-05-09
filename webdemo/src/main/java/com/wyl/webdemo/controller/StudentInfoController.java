package com.wyl.webdemo.controller;


import com.wyl.webdemo.annotation.AnalysisActuator;
import com.wyl.webdemo.common.enums.ModuleEnums;
import com.wyl.webdemo.common.enums.OperationKinds;
import com.wyl.webdemo.dto.R;
import com.wyl.webdemo.dto.StatusDTO;
import com.wyl.webdemo.dto.StudentInfoDTO;
import com.wyl.webdemo.entity.local.StudentInfo;
import com.wyl.webdemo.service.local.StudentInfoService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.ws.Response;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyl123
 * @since 2018-10-08
 */
@RestController
@RequestMapping("/studentInfo")
public class StudentInfoController {

    @Resource
    private StudentInfoService studentInfoService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public R<StudentInfo> getStudentInfo(Integer id){
        return new R(studentInfoService.getStudentById(id, 123L, 123L));
    }

    @AnalysisActuator(module = ModuleEnums.STUDENT, oper = OperationKinds.UPDATE_STUTAS)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R<Integer> deleteStudent(StatusDTO dto){
        return new R(studentInfoService.deleteStudentById(dto.getId(), 123L, 123L, dto.getActiveFlag()));
    }

    @AnalysisActuator(module = ModuleEnums.STUDENT, oper = OperationKinds.ADD, opDetail = "添加学员blabla")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R<Integer> addStudentInfo(StudentInfo studentInfo){
        return new R(studentInfoService.addStudentById(studentInfo, 123L, 123L));
    }

    @AnalysisActuator(module = ModuleEnums.STUDENT, oper = OperationKinds.EDIT)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public R<Integer> editStudentInfo(StudentInfoDTO dto){
        return new R(studentInfoService.editStudentById(158L, 3794L, dto));
    }

}

