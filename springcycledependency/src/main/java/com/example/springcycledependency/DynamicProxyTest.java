package com.example.springcycledependency;

import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    public static void main(String[] args) {
        IUserService target = new UserServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(target);

        IUserService proxyTarget = (IUserService) Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(), target.getClass().getInterfaces(), handler);
        proxyTarget.add("yytest");
    }
}
