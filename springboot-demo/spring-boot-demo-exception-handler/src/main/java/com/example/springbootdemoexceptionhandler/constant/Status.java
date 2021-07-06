package com.example.springbootdemoexceptionhandler.constant;

import lombok.Getter;

@Getter
public enum Status {
    OK(200, "OK"),

    UNKNOWN_ERROR(500, "服务器错误");

    private Integer code;

    private String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
