package com.example.mybatisspringbootdemo.mapper;

import com.example.mybatisspringbootdemo.MybatisSpringBootDemoApplicationTests;
import com.example.mybatisspringbootdemo.mybatis.entity.User;
import com.example.mybatisspringbootdemo.mybatis.mapper.UserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserMapperTest extends MybatisSpringBootDemoApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void selectAllUser() {
        List<User> allUsers = userMapper.selectAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(allUsers));
    }
}
