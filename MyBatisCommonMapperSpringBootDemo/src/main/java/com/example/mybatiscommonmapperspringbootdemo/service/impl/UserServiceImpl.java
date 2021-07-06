package com.example.mybatiscommonmapperspringbootdemo.service.impl;

import com.example.mybatiscommonmapperspringbootdemo.mybatis.entity.User;
import com.example.mybatiscommonmapperspringbootdemo.mybatis.mapper.UserMapper;
import com.example.mybatiscommonmapperspringbootdemo.pojo.UserVO;
import com.example.mybatiscommonmapperspringbootdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public UserVO getUserByID(int id) {
        User user = userMapper.selectByPrimaryKey(id);
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setName(user.getName());
        userVO.setAddress(user.getAddress());
        return userVO;
    }

    @Override
    @CacheEvict(value = "user", key = "#id")
    public void deleteByID(int id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    @CachePut(value = "user", key = "#result.id")
    public UserVO save(UserVO userVO) {
        User user = new User();
        user.setName(userVO.getName());
        user.setAddress(userVO.getAddress());
        userMapper.insert(user);
        userVO.setId(user.getId());
        log.info("user vo:{}", userVO);
        return userVO;
    }
}
