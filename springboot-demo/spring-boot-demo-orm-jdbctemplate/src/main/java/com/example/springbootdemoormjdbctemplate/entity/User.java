package com.example.springbootdemoormjdbctemplate.entity;

import com.example.springbootdemoormjdbctemplate.annotation.Column;
import com.example.springbootdemoormjdbctemplate.annotation.Pk;
import com.example.springbootdemoormjdbctemplate.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: spring-boot-demo-orm-jdbctemplate
 * TODO
 * @author: Yejiaxin
 * @create: 2020-07-21 10:18
 */

@Data
@Table(name = "orm_user")
public class User implements Serializable {

    @Pk
    private Long id;

    private String name;

    private String password;

    private String salt;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;
}
