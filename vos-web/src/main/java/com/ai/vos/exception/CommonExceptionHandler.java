package com.ai.vos.exception;

import com.ai.vos.domain.common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @program: vos-web
 * 全局异常处理
 * @author: yejx
 * @create: 2020-09-28 20:26
 */
@ControllerAdvice(basePackages = {"com.ai.vos.controller"})
public class CommonExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    private final MessageSource messageSource;

    public CommonExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResponse<Object> handlerCommonException(Exception e) {
        logger.error(e.getMessage(), e);
        Object[] args = new  Object[]{e.getMessage()};
        String message = messageSource.getMessage("720000", args, null);
        return ApiResponse.markError(message);
    }
}
