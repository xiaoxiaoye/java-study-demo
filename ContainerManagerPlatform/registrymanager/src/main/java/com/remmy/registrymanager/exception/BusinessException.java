package com.remmy.registrymanager.exception;

import com.remmy.registrymanager.utils.I18nUtils;

public class BusinessException extends RuntimeException {
    static final long serialVersionUID = -1L;

    private final String errorCode;
    private final Object[] formatArgs;

    public BusinessException(String errorCode, Object... args) {
        this.errorCode = errorCode;
        this.formatArgs = args;
    }

    public BusinessException(String errorCode, Throwable e, Object... args) {
        super(e);
        this.errorCode = errorCode;
        this.formatArgs = args;
    }

    @Override
    public String getLocalizedMessage() {
        return I18nUtils.getMessage(errorCode, formatArgs, "");
    }

    @Override
    public String getMessage() {
        return getLocalizedMessage();
    }
}
