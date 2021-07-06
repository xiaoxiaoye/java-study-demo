package com.remmy.registrymanager.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("仓库镜像")
public class Image {
    /**
     * ID
     */
    @ApiModelProperty(value = "序号", example = "0")
    Integer id;
    /**
     * 镜像ID
     */
    @ApiModelProperty("镜像ID")
    String imageId;
    /**
     * 镜像名称
     */
    @ApiModelProperty("镜像名称")
    String name;
    /**
     * 镜像tag
     */
    @ApiModelProperty("镜像tag")
    String tag;
    /**
     * 镜像大小, 单位byte
     */
    @ApiModelProperty(value = "镜像大小", example = "0")
    Integer size;
    /**
     * 镜像配置详情
     */
    @ApiModelProperty("镜像配置详情")
    String config;
    /**
     * 仓库地址
     */
    @ApiModelProperty("仓库地址")
    String registryAddress;
    /**
     * 仓库名称
     */
    @ApiModelProperty("仓库名称")
    String registryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public String getRegistryName() {
        return registryName;
    }

    public void setRegistryName(String registryName) {
        this.registryName = registryName;
    }

    @Override
    public String toString() {
        return "Image [config=" + config + ", id=" + id + ", imageId=" + imageId + ", name=" + name
                + ", registryAddress=" + registryAddress + ", registryName=" + registryName + ", size=" + size
                + ", tag=" + tag + "]";
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
