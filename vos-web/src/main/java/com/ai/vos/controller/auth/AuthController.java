package com.ai.vos.controller.auth;

import com.ai.vos.common.Status;
import com.ai.vos.domain.common.ApiResponse;
import com.ai.vos.domain.auth.LoginRequest;
import com.ai.vos.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ai.vos.exception.SecurityException;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: vos-web
 * 用户登录
 * @author: yejx
 * @create: 2020-09-29 15:47
 */
@Api("Auth")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/api/auth/login")
    public ApiResponse<String> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.createJWT(authentication, loginRequest.getRememberMe());
        return ApiResponse.markSuccess("Bearer "+jwt);
    }

    @ApiOperation(value = "用户登出", notes = "用户登出")
    @PostMapping("/api/auth/logout")
    public ApiResponse<Object> logout(HttpServletRequest request) {
        try {
            jwtUtil.invalidateJWT(request);
        } catch (SecurityException e) {
            throw new SecurityException(Status.UNAUTHORIZED);
        }
        return ApiResponse.ofStatus(Status.SUCCESS, null);
    }

}
