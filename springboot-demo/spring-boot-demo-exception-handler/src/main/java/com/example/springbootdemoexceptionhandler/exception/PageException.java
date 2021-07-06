package com.example.springbootdemoexceptionhandler.exception;

import com.example.springbootdemoexceptionhandler.constant.Status;
import lombok.Getter;

@Getter
public class PageException extends BaseException {
    public PageException(String message, Integer code) {
        super(message, code);
    }

    public PageException(Status status) {
        super(status);
    }
}
