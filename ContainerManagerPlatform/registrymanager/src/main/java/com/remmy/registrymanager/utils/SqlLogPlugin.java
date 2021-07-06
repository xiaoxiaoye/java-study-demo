package com.remmy.registrymanager.utils;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Component;

// @Component
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class SqlLogPlugin implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(SqlLogPlugin.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long t = System.currentTimeMillis();
        Object result = invocation.proceed();

        long cost = System.currentTimeMillis()-t;
        if(cost > 10){
            logger.debug("{}.{} execute sql cost {}ms", invocation.getTarget().getClass().getName(),invocation.getMethod().getName(),cost);
        }
        return result;
    }
}