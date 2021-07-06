package com.example.springcycledependency;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAop {
    Logger logger = LoggerFactory.getLogger(ControllerAop.class);

    @Pointcut("execution(* com.example.springcycledependency.UserServiceImpl.add(..))")
    public void controllerLog(){}

    @Before("controllerLog()")
    public void beforeController(JoinPoint joinPoint) {
        logger.info("准备向数据库中插入数据");
    }


    @After("controllerLog()")
    public void afterController(JoinPoint joinPoint) {
        logger.info("插入数据库成功");
    }
}
