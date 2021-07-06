package com.remmy.registrymanager.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.remmy.registrymanager.utils.RegistryType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("镜像仓库")
public class Registry implements Serializable {
    private static final long serialVersionUID = -1L;
    /**
     * 仓库名称
     */
    @ApiModelProperty("仓库名称")
    @NotNull
    private String name;

    /**
     * 主机IP地址
     */
    @ApiModelProperty("主机IP地址")
    @NotNull
    private String host;

    /**
     * 端口
     */
    @ApiModelProperty(value = "端口", example = "22")
    @NotNull
    private Integer port;

    /**
     * 传输协议
     */
    @ApiModelProperty("接口协议[http/https]")
    @NotNull
    private String protocol;

    /**
     * 仓库类型
     */
    @ApiModelProperty("仓库类型")
    @NotNull
    private RegistryType registryType;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String user;

    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    private String password;

    /**
     * harbor仓库的项目名称
     */
    @ApiModelProperty("harbor仓库的项目名称")
    private String harborProductName;

    /**
     * harbor仓库的项目id
     */
    @ApiModelProperty(value = "harbor仓库的项目id", example = "0")
    private Integer harborProductId;

    /**
     * 仓库是否健康
     */
    @ApiModelProperty(value = "仓库是否健康", example="0")
    private Integer health;

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

    public String getHarborProductName() {
        return harborProductName;
    }

    public void setHarborProductName(String harborProductName) {
        this.harborProductName = harborProductName;
    }

    public Integer getHarborProductId() {
        return harborProductId;
    }

    public void setHarborProductId(Integer harborProductId) {
        this.harborProductId = harborProductId;
    }

    public RegistryType getRegistryType() {
        return registryType;
    }

    public void setRegistryType(RegistryType registryType) {
        this.registryType = registryType;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
