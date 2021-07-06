package com.remmy.registrymanager.utils;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class I18nUtils {
    private I18nUtils(){}

    public static String getMessage(String errorCode, Object[] args, String defaultMessage) {
        MessageSource messageSource = ApplicationContextHolder.getInstance().getBean(MessageSource.class);
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(errorCode, args, defaultMessage, locale);
    }
}