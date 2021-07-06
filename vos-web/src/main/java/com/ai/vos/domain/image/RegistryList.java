package com.ai.vos.domain.image;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class RegistryList {
    @JSONField(name = "List")
    private List<Registry> registryList;

    public List<Registry> getRegistryList() {
        return registryList;
    }

    public void setRegistryList(List<Registry> registryList) {
        this.registryList = registryList;
    }

    public static class Registry {
        private String name;

        private String host;

        private Integer port;

        private String user;

        @JSONField(name = "passwd")
        private String password;

        private String type;

        private String scheme;

        @JSONField(name = "product_name")
        private String productName;

        private Boolean delete;

        private String state;

        private boolean isCommon;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Boolean getDelete() {
            return delete;
        }

        public void setDelete(Boolean delete) {
            this.delete = delete;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public boolean isCommon() {
            return isCommon;
        }

        public void setCommon(boolean common) {
            isCommon = common;
        }
    }


}
