package com.example.springbootdemoormjdbctemplate.dao;

import com.example.springbootdemoormjdbctemplate.dao.base.BaseDao;
import com.example.springbootdemoormjdbctemplate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: spring-boot-demo-orm-jdbctemplate
 * TODO
 * @author: Yejiaxin
 * @create: 2020-07-21 22:23
 */

@Repository
public class UserDao extends BaseDao<User, Long> {

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public Integer insert(User user) {
        return super.insert(user, true);
    }

    public Integer delete(Long id) {
        return super.deleteByID(id);
    }

    public Integer update(User user, Long id) {
        return super.updateByID(user, id, true);
    }

    public User selectByID(Long id) {
        return super.findOneByID(id);
    }

    public List<User> selectUserList(User user) {
        return super.findByExample(user);
    }
}
