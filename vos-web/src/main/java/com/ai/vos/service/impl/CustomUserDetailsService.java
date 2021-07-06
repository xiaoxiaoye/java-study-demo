package com.ai.vos.service.impl;

import com.ai.vos.domain.auth.CustomUserDetail;
import com.ai.vos.persistence.entity.SysUser;
import com.ai.vos.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.Date;

/**
 * @program: vos-web
 * TODO
 * @author: yejx
 * @create: 2020-09-29 23:04
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserMapper.selectOneByExample(
                Example.builder(SysUser.class)
                        .where(WeekendSqls.<SysUser>custom()
                                .andEqualTo(SysUser::getName, username)).build());

        CustomUserDetail userDetails = new CustomUserDetail();
        userDetails.setId(user.getUserId().longValue());
        userDetails.setUsername(user.getName());
        userDetails.setPassword(user.getPassword());
        if (user.getLastModifyPwdTime() != null) {
            userDetails.setCreateTime(user.getLastModifyPwdTime().getTime());
        }
        userDetails.setCreateTime(new Date().getTime());
        return userDetails;
    }
}
