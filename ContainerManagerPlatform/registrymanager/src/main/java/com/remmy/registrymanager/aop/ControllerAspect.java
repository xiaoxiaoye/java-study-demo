package com.remmy.registrymanager.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.remmy.registrymanager.annotation.ActionLogAnnotation;
import com.remmy.registrymanager.config.SysConfiguration;
import com.remmy.registrymanager.entity.ActionLog;
import com.remmy.registrymanager.service.ActionLogService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ControllerAspect {
    @Autowired
    private ActionLogService logService;

    @Autowired
    private SysConfiguration config;

    @Pointcut("execution(* com.remmy.registrymanager.controller.*.*(..))")
    private void controllerPackage() {
    }

    @AfterReturning("controllerPackage()")
    public void logAction(JoinPoint joinPoint) {
        if (!config.isLogAction())
            return;
        ActionLog actionLog = new ActionLog();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 记录操作名称
        ActionLogAnnotation logAnnotation = method.getAnnotation(ActionLogAnnotation.class);
        if (logAnnotation != null) {
            actionLog.setOperation(logAnnotation.value());
        }

        // 记录方法名
        String classname = joinPoint.getTarget().getClass().getName();
        String methodname = method.getName();
        actionLog.setMethod(classname + "." + methodname);

        // 记录IP
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes()))
                .getRequest();
        actionLog.setIp(request.getRemoteHost());

        // TODO 记录用户名，参数

        logService.logAction(actionLog);
    }
}