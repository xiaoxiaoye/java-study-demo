package com.example.mybatisdemo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

/**
 * @program: mybatis-demo
 * TODO
 * @author: Yejiaxin
 * @create: 2020-07-20 12:57
 */
@Slf4j
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
})
public class SqlCostTimeInterceptor implements Interceptor {
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        long startTime = System.currentTimeMillis();
        try{
            return invocation.proceed();
        } finally {
            long costTime = System.currentTimeMillis() - startTime;
            String sql = statementHandler.getBoundSql().getSql();
            log.info("执行SQL [{}] 耗时 [{}] ms", sql, costTime);
        }
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置的信息：" + properties);
    }
}
