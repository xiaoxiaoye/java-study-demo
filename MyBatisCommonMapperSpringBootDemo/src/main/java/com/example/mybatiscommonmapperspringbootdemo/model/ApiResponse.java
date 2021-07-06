package com.example.mybatiscommonmapperspringbootdemo.model;

import com.example.mybatiscommonmapperspringbootdemo.common.Status;
import com.example.mybatiscommonmapperspringbootdemo.exception.BaseException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "通用接口返回")
public class ApiResponse {

    @ApiModelProperty(value = "通用返回码", required = true)
    Integer code;

    @ApiModelProperty(value = "通用返回信息", required = true)
    String message;

    @ApiModelProperty(value = "通用返回数据", required = true)
    Object data;

    private ApiResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse of(Integer code, String message, Object data) {
        return new ApiResponse(code, message, data);
    }

    public static ApiResponse ofStatus(Status status, Object data) {
        return of(status.getCode(), status.getMessage(), data);
    }

    public static ApiResponse ofSuccess(Object data) {
        return ofStatus(Status.OK, data);
    }

    public static ApiResponse markSuccess() {
        return ofStatus(Status.OK, null);
    }

    public static ApiResponse ofStatus(Status status) {
        return ofStatus(status, null);
    }

    public static <T extends BaseException> ApiResponse ofException(T e) {
        return of(e.getCode(), e.getMessage(), null);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
