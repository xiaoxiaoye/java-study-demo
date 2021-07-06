package org.example.mybatisdemo;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{userId}")
    User getUserInfo(@Param("userId") int userId);

    User getUser(int userId);
}
