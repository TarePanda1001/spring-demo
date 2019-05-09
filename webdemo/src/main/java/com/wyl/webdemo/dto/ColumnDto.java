package com.wyl.webdemo.dto;

import lombok.Data;

@Data
public class ColumnDto {
    private String columnName;
    private String columnType;
    private String columnKey;
    private String columnComment;
}

