package com.wyl.webdemo.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum DataStatus {
    NORMAL(0, "启用"),
    DELETE(1, "删除"),
    ;

    private Integer value;
    private String desc;

    DataStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }



}
