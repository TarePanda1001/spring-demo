package com.wyl.webdemo.dto;

import com.wyl.webdemo.entity.local.StudentInfo;
import lombok.Data;

@Data
public class StudentInfoDTO extends StudentInfo {
    private Integer num;
}
