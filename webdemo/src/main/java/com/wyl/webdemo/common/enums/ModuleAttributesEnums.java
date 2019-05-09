package com.wyl.webdemo.common.enums;

import org.apache.commons.lang3.StringUtils;

public enum  ModuleAttributesEnums {
    STUDENT_NAME(ModuleEnums.STUDENT.getModule(), "name", "姓名"),
    STUDENT_AGE(ModuleEnums.STUDENT.getModule(), "age", "年龄"),
    STUDENT_NUMBER(ModuleEnums.STUDENT.getModule(), "number", "编号"),
    STUDENT_PASSWORD(ModuleEnums.STUDENT.getModule(), "passWord", "密码"),
    ;

    private Integer module;
    private String argName;
    private String argDesc;

    ModuleAttributesEnums(Integer module, String argName, String argDesc) {
        this.module = module;
        this.argName = argName;
        this.argDesc = argDesc;
    }

    public Integer getModule() {
        return module;
    }

    public String getArgName() {
        return argName;
    }

    public String getArgDesc() {
        return argDesc;
    }

    public static String getArgDesc(Integer module, String argName){
        for (ModuleAttributesEnums attribute : ModuleAttributesEnums.values()){
            if(attribute.getModule().equals(module) && attribute.getArgName().equals(argName)){
                return attribute.getArgDesc();
            }
        }
        return StringUtils.EMPTY;
    }

}
