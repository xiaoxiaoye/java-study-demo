package com.ai.vos.utils;

import com.ai.vos.domain.common.ApiResponse;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: vos-web
 * TODO
 * @author: yejx
 * @create: 2020-10-01 16:54
 */
public class ResponseUtil {
    public static void renderJson(HttpServletResponse response, ApiResponse<Object> res) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(200);
        response.getWriter().write(JSON.toJSONString(res));
    }
}
