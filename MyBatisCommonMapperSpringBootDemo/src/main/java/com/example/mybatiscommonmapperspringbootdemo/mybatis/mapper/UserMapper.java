package com.example.mybatiscommonmapperspringbootdemo.mybatis.mapper;

import com.example.mybatiscommonmapperspringbootdemo.mybatis.entity.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Component
public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
}