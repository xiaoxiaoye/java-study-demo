package com.ai.vos.domain.common;

import com.ai.vos.common.Status;
import com.ai.vos.exception.BaseException;

/**
 * @program: vos-web
 * TODO
 * @author: yejx
 * @create: 2020-09-23 14:58
 */
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;

    public ApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }


    public static <E> ApiResponse<E> markSuccess() {
        return new ApiResponse<>(true, null);
    }

    public static <E> ApiResponse<E> markSuccess(E data) {
        return new ApiResponse<>(true, data);
    }

    public static <E> ApiResponse<E> markError(String message) {
        return new ApiResponse<E>(false, null, message);
    }

    public static <E> ApiResponse<E> ofStatus(Status status, E data) {
        return new ApiResponse<E>(false, data, status.getMessage());
    }

    public static <E extends BaseException> ApiResponse<Object> ofException(E e) {
        return new ApiResponse<>(false, e.getData(), e.getMessage());
    }
}
