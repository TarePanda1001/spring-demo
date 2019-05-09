package com.wyl.webdemo.dto;

import lombok.Data;

@Data
public class SchemaDto {

    private String tableSchema;
    private String tableName;

    @Override
    public String toString(){
        return tableSchema + "." + tableName;
    }
}
