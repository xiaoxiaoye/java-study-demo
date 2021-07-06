package com.example.mybatiscommonmapperspringbootdemo.common;


public enum Status {
    OK(200, "OK"),
    UNKNOWN(500, "unknown error");

    private final Integer code;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
