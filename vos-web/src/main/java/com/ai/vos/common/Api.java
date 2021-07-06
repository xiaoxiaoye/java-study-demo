package com.ai.vos.common;

/**
 * @program: vos-web
 * 后端模块接口定义枚举
 * @author: yejx
 * @create: 2020-09-27 11:06
 */
public class Api {
    private static final String URL_SCHEMA = "http://";

    private interface ApiI {
        String url(String... args);
    }

    public enum Registry implements ApiI {
        REGISTRY_LIST_QUERY("/registry"),
        REGISTRY_OPERATE("/registry");

        private final String URLPattern;
        private final String domain = "registry";

        Registry(String URLPattern) {
            this.URLPattern = URLPattern;
        }

        @Override
        public String url(String... args) {
            return URL_SCHEMA + domain + URLPattern;
        }
    }
}
