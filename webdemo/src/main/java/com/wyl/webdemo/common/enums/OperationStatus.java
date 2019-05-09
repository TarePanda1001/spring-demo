package com.wyl.webdemo.common.enums;

public enum OperationStatus {
    FAILED(0, "失败"),
    SUCCESS(1, "成功"),
    ;

    private int value;
    private String label;

    OperationStatus(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
