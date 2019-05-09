package com.wyl.webdemo.common.enums;

public enum DataSourceEnums {
    LOCAL(0, "local", "本地集群"),
    PROD(1, "prod", "Prod集群"),
    TIANXIAO(2, "tianxiao", "Tianxiao集群"),
    HERMES(3, "hermes", "Hermes集群"),
    SERVICE(4, "service", "Service集群"),
    ;

    private Integer code;
    private String name;
    private String label;

    DataSourceEnums(Integer code, String name, String label) {
        this.code = code;
        this.name = name;
        this.label = label;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }
}
