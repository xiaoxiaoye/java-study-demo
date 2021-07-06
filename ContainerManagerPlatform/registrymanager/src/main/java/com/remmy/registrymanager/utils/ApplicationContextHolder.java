package com.remmy.registrymanager.utils;

import org.springframework.context.ApplicationContext;

public class ApplicationContextHolder {
    private static final ApplicationContextHolder instance = new ApplicationContextHolder();

    private ApplicationContext applicationContext;

    private ApplicationContextHolder() {

    }

    public static ApplicationContextHolder getInstance() {
        return instance;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }
}
