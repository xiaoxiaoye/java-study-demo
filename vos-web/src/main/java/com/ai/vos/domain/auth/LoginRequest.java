package com.ai.vos.domain.auth;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import java.util.Objects;

/**
 * @program: vos-web
 * 用户登录请求
 * @author: yejx
 * @create: 2020-09-29 15:50
 */
public class LoginRequest {
    /**
     * 用户名
     */
    @NonNull
    @ApiModelProperty(notes = "用户名")
    private String username;

    /**
     * 密码
     */
    @NonNull
    @ApiModelProperty(notes = "密码")
    private String password;


    @ApiModelProperty(notes = "记住我")
    private Boolean rememberMe = false;

    public LoginRequest() {
    }

    public LoginRequest(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    public LoginRequest(@NonNull String username, @NonNull String password, Boolean rememberMe) {
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginRequest that = (LoginRequest) o;
        return username.equals(that.username) &&
                password.equals(that.password) &&
                Objects.equals(rememberMe, that.rememberMe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, rememberMe);
    }
}
