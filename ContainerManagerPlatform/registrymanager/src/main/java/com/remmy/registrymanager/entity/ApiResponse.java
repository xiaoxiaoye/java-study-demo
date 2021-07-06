package com.remmy.registrymanager.entity;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <E> ApiResponse<E> markSuccess() {
        return new ApiResponse<>(true, "", null);
    }

    public static <E> ApiResponse<E> markSuccess(E data) {
        return new ApiResponse<>(true, "", data);
    }

    public static <E> ApiResponse<E> makrError(String message) {
        return new ApiResponse<>(false, message, null);
    }

    public static <E> ApiResponse<E> makrError() {
        return new ApiResponse<>(false, "", null);
    }
}
