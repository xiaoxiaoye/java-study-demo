package com.ai.vos.config;

import com.ai.vos.common.Status;
import com.ai.vos.domain.common.ApiResponse;
import com.ai.vos.exception.SecurityException;
import com.ai.vos.service.impl.CustomUserDetailsService;
import com.ai.vos.utils.JwtUtil;
import com.ai.vos.utils.ResponseUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: vos-web
 * jwt过滤器， 用于验证请求中的jwt
 * @author: yejx
 * @create: 2020-09-30 11:28
 */
@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (checkIgnores(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 验证jwt
        String jwt = jwtUtil.getJWTFromRequest(request);
        if (StringUtils.isNotBlank(jwt)) {
            try {
                String username = jwtUtil.getUsernameFromJWT(jwt);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // 继续下一个filter处理
                filterChain.doFilter(request, response);
            } catch (SecurityException e) {
                ResponseUtil.renderJson(response, ApiResponse.ofException(e));
            }
        } else {
            ResponseUtil.renderJson(response, ApiResponse.ofStatus(Status.TOKEN_EXPIRED, null));
        }
    }

    /**
     * 登录和登出操作请求不需要验证jwt, 直接忽略
     * @param request 请求
     * @return 是否忽略
     */
    private boolean checkIgnores(@NonNull HttpServletRequest request) {
        String method = request.getMethod();
        Set<String> ignores = new HashSet<>();
        ignores.add("/api/auth/**");

        for (String ignore : ignores) {
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(ignore, method);
            if (matcher.matches(request)) {
                return true;
            }
        }
        return false;
    }
}
