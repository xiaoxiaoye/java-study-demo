package com.ai.vos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: vos-web
 * TODO
 * @author: yejx
 * @create: 2020-09-30 06:45
 */

@ConfigurationProperties(prefix = "jwt.config")
public class JwtConfig {
    /**
     * jwt 加密key
     */
    private String key = "123456";

    /**
     * jwt 过期时间 默认值：600000 {@code 10 分钟}.
     */
    private Long ttl = 600000L;

    /**
     * 开启 记住我 之后 jwt 过期时间，默认值 604800000 {@code 7天}
     */
    private Long remember = 604800000L;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    public Long getRemember() {
        return remember;
    }

    public void setRemember(Long remember) {
        this.remember = remember;
    }
}
