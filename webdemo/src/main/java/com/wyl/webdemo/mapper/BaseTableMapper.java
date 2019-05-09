package com.wyl.webdemo.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface BaseTableMapper {
    @Select("select table_name,table_comment from information_schema.TABLES where TABLE_SCHEMA=#{schema}")
    List<Map> getListTablesByDataBase(String schema);

    @Select("select table_schema,table_name,table_type,table_comment from information_schema.TABLES")
    List<Map> allTables();

    @Select("select table_schema,table_name,column_name,column_type,column_key,column_comment from information_schema.COLUMNS where TABLE_NAME=#{tableName}")
    List<Map> listTableColumns(String tableName);
}
