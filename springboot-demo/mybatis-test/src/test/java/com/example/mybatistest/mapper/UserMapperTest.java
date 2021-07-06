package com.example.mybatistest.mapper;

import com.example.mybatistest.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Slf4j
@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void selectAllUser() {
        List<User> userList = userMapper.selectAllUser();
        log.debug("user list:{}", userList);
    }

    @Test
    void saveUser() {
        User user = User.builder()
                .name("testSave3")
                .password("123")
                .salt("ddd")
                .email("")
                .phoneNumber("1111")
                .status(1)
                .build();
        int i = userMapper.saveUser(user);
        Assertions.assertEquals(1, i);
    }

    @Test
    void deleteById() {
        int i = userMapper.deleteById(1L);
        Assertions.assertEquals(1,i);
    }
}