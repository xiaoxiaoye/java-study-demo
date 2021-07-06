package com.example.springbootdemoexceptionhandler.exception;

import com.example.springbootdemoexceptionhandler.constant.Status;
import lombok.Getter;

@Getter
public class JsonException extends BaseException {
    public JsonException(Status status) {
        super(status);
    }

    public JsonException(String message, Integer code) {
        super(message, code);
    }
}
