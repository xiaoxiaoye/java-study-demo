package com.example.mybatiscommonmapperspringbootdemo.service;

import com.example.mybatiscommonmapperspringbootdemo.mybatis.entity.User;
import com.example.mybatiscommonmapperspringbootdemo.pojo.UserVO;

public interface UserService {
    /**
     * 根据ID查询User对象
     * @param id
     * @return
     */
    UserVO getUserByID(int id);

    /**
     * 删除
     * @param id
     * @return
     */
    void deleteByID(int id);

    /**
     * 保存
     * @param user
     * @return
     */
    UserVO save(UserVO user);
}
