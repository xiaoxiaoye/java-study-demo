package com.example.springcycledependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserService userService;

    @Override
    public void add(String name) {
        System.out.println("向数据库中插入名为：  "+name+" 的用户");
    }
}
