package com.wyl.webdemo.dto;

import lombok.Data;

@Data
public class R<T> {
    private int code = 0;            // 正常返回代码
    private String message = "";
    private String detailMessage = "";
    private T data;

    public R() {
        super();
    }

    /**
     * 正常返回值
     *
     * @param data
     */
    public R(T data) {
        super();
        this.data = data;
    }

}
