package com.wyl.webdemo.common.enums;

public enum ColumnAttributeEnums {
    TABLE_SCHEMA(0,"TABLE_SCHEMA","table_schema"),
    TABLE_NAME(1,"TABLE_NAME","table_name"),
    COLUMN_NAME(2,"COLUMN_NAME","column_name"),
    COLUMN_TYPE(3,"COLUMN_TYPE","column_type"),
    COLUMN_KEY(4,"COLUMN_KEY","column_key"),
    COLUMN_COMMENT(5,"COLUMN_COMMENT","column_comment");

    private Integer type;
    private String label;
    private String lowerLabel;


    ColumnAttributeEnums(Integer type, String label, String lowerLabel) {
        this.type = type;
        this.label = label;
        this.lowerLabel = lowerLabel;
    }

    public Integer getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public String getLowerLabel() {
        return lowerLabel;
    }

}
