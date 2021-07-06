package com.example.mybatiscommonmapperspringbootdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
@Aspect
public class AopLog {
    private static final String START_TIME = "request-start";

    @Pointcut("execution(public * com.example.mybatiscommonmapperspringbootdemo.controller.*Controller.*(..))")
    public void log() {

    }

    @Before("log()")
    public void beforeLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("【请求URL】：{}", request.getRequestURL());
        log.info("【请求IP】: {}", request.getRemoteAddr());
        log.info("【请求类名】：{}，【请求方法名】：{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

        Long start = System.currentTimeMillis();
        request.setAttribute(START_TIME, start);
    }


    @AfterReturning("log()")
    public void afterLog() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Long start = (Long) request.getAttribute(START_TIME);
        Long end = System.currentTimeMillis();

        log.info("【请求耗时】：{}ms", end-start);
    }


}
