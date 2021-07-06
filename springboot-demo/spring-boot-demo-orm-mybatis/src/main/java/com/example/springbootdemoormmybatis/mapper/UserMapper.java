package com.example.springbootdemoormmybatis.mapper;

import com.example.springbootdemoormmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    @Select("SELECT * FROM orm_user")
    List<User> selectAllUser();

    @Select("SELECT * FROM orm_user WHERE id = #{id}")
    User selectUserByID(@Param("id") Long id);

    int saveUser(@Param("user") User user);

    int deleteByID(@Param("id") Long id);
}
