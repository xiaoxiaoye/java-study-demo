package com.ai.vos.persistence.mapper;

import com.ai.vos.persistence.entity.SysUser;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface SysUserMapper extends Mapper<SysUser> {
}