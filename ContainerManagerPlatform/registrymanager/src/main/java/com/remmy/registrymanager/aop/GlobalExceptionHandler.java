package com.remmy.registrymanager.aop;

import com.remmy.registrymanager.entity.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice("com.remmy.registrymanager.controller")
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResponse<Object> handleControllerException(Exception exception) {
        return ApiResponse.makrError(exception.getMessage());
    }
}