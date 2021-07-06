package com.example.mybatiscommonmapperspringbootdemo.exception;

import com.example.mybatiscommonmapperspringbootdemo.common.Status;
import lombok.Getter;

@Getter
public class UserNotFoundException extends BaseException {
    /**
     *
     */
    private static final long serialVersionUID = -3743418943066097475L;

    public UserNotFoundException(Status status) {
        super(status);
    }

    public UserNotFoundException(Integer code, String message) {
        super(code, message);
    }
}
