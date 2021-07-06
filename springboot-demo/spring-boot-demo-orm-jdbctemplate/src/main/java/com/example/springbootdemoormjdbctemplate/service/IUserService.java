package com.example.springbootdemoormjdbctemplate.service;

import com.example.springbootdemoormjdbctemplate.entity.User;

import java.util.List;

/**
 * @program: spring-boot-demo-orm-jdbctemplate
 * TODO
 * @author: Yejiaxin
 * @create: 2020-07-21 22:34
 */
public interface IUserService {
    Boolean save(User user);

    Boolean delete(Long id);

    Boolean update(User user, Long id);

    User getUser(Long id);

    List<User> getUser(User user);
}
