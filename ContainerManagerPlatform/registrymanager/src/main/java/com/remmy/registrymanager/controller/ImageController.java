package com.remmy.registrymanager.controller;

import java.util.List;

import com.remmy.registrymanager.entity.ApiResponse;
import com.remmy.registrymanager.entity.Image;
import com.remmy.registrymanager.service.ImageService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "镜像接口", description = "仓库镜像相关操作")
@RestController
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @ApiOperation("获取镜像列表")
    @GetMapping("/images")
    public ApiResponse<List<Image>> listImages(@RequestParam @ApiParam("仓库名称") String registryName,
            @RequestParam(required = false, defaultValue = "0") @ApiParam(value = "分页序号", example = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "0") @ApiParam(value = "分页大小", example = "1") Integer pageSize)
            throws Exception {
        List<Image> result = imageService.listImages(registryName, pageSize, pageNum);
        return ApiResponse.markSuccess(result);
    }

    @ApiOperation("获取镜像详细配置")
    @GetMapping("/images/{imageId}")
    public ApiResponse<Boolean> getImageConfig(@PathVariable String imageId) {
        throw new RuntimeException("获取仓库配置异常");
    }
}
