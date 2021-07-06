package com.remmy.registrymanager.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ApiLogReport implements HandlerInterceptor {
    private static final Logger loggger = LoggerFactory.getLogger(ApiLogReport.class);

    private static final String API_COST_TIME = "api_cost";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        Long startTime = (Long)request.getAttribute(API_COST_TIME);
        loggger.debug("URI {} cost {}ms", request.getRequestURI(), (System.currentTimeMillis()-startTime));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.setAttribute(API_COST_TIME, System.currentTimeMillis());
        return true;
    }
}
