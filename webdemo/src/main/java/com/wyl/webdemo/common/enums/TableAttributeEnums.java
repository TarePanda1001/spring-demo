package com.wyl.webdemo.common.enums;

public enum TableAttributeEnums {
    TABLE_NAME(0, "TABLE_NAME", "table_name"),
    TABLE_COMMENT(1, "TABLE_COMMENT", "table_comment");

    private Integer code;
    private String label;
    private String lowerLabel;

    TableAttributeEnums(Integer code, String label, String lowerLabel) {
        this.code = code;
        this.label = label;
        this.lowerLabel = lowerLabel;
    }

    public Integer getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public String getLowerLabel() {
        return lowerLabel;
    }
}
