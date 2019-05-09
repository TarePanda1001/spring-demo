package com.wyl.webdemo.common.enums;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum ModuleEnums {
    STUDENT(1, "学生", "student_info"),
    ;
    private Integer module;
    private String moduleName;
    private String tableName;

    ModuleEnums(Integer module, String moduleName, String tableName) {
        this.module = module;
        this.moduleName = moduleName;
        this.tableName = tableName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public Integer getModule() {
        return module;
    }

    public String getTableName() {
        return tableName;
    }
}
