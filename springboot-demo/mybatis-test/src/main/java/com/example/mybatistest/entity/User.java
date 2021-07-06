package com.example.mybatistest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User  implements Serializable {

    private  Long id;

    private  String name;

    private  String password;

    private String salt;

    private String email;

    private String phoneNumber;

    private  Integer status;

    private Date createTime;

    private  Date lastLoginTime;

    private Date lastUpdateTime;
}
