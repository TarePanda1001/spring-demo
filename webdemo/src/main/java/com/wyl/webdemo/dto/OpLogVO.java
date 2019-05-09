package com.wyl.webdemo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OpLogVO {
    /**
     * 员工id
     */
    private String staffName;
    /**
     * 操作的详细记录
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createTime;
}
