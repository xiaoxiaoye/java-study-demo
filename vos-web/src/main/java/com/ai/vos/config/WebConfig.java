package com.ai.vos.config;

import com.ai.vos.common.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: vos-web
 * web相关配置
 * @author: yejx
 * @create: 2020-09-28 13:12
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private ContextHolder contextHolder;

    /**
     * 配置request拦截器， 获取clusterId作为后续会话内容
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String clusterID = request.getParameter("clusterId");
                if (!"".equals(clusterID)) {
                    contextHolder.setCurrentClusterID(clusterID);
                }
                return true;
            }
        }).addPathPatterns("/**").excludePathPatterns("/static/**","index.html","check.html");
    }
}

