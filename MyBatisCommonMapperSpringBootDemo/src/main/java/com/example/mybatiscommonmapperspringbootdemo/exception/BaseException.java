package com.example.mybatiscommonmapperspringbootdemo.exception;

import com.example.mybatiscommonmapperspringbootdemo.common.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -5040183486700534391L;
    private Integer code;
    private String message;

    public BaseException(Status status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
