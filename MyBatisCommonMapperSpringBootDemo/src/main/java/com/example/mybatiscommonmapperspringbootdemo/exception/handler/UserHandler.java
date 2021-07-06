package com.example.mybatiscommonmapperspringbootdemo.exception.handler;

import com.example.mybatiscommonmapperspringbootdemo.exception.BaseException;
import com.example.mybatiscommonmapperspringbootdemo.exception.UserNotFoundException;
import com.example.mybatiscommonmapperspringbootdemo.model.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ApiResponse userNotFoundErrorHandler(UserNotFoundException e) {
        return ApiResponse.ofException(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse unknowException(Exception e) {
        return ApiResponse.ofException(new BaseException(500, e.getMessage()));
    }
}
