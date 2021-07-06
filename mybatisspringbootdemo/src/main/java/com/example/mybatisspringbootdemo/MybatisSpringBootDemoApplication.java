package com.example.mybatisspringbootdemo;

import com.example.mybatisspringbootdemo.mybatis.entity.User;
import com.example.mybatisspringbootdemo.mybatis.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
//@MapperScan(basePackages = {"com.example.mybatisspringbootdemo.mybatis.mapper"})
public class MybatisSpringBootDemoApplication implements CommandLineRunner {
    private final UserMapper userMapper;

    // 构造器注入
    MybatisSpringBootDemoApplication(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(MybatisSpringBootDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            User user = User.builder().name("user" + i)
                    .address("address" + i)
                    .build();
            userMapper.insert(user);
        }

        PageHelper.startPage(1, 4);
        userMapper.selectAll().stream().forEach(System.out::println);

        System.out.println("=================");

        PageHelper.startPage(2, 4);
        userMapper.selectAll().stream().forEach(System.out::println);
    }
}
