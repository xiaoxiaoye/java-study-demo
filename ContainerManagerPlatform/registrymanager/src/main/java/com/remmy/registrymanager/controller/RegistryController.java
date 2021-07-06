package com.remmy.registrymanager.controller;

import java.util.List;

import javax.validation.Valid;

import com.remmy.registrymanager.annotation.ActionLogAnnotation;
import com.remmy.registrymanager.entity.ApiResponse;
import com.remmy.registrymanager.entity.Registry;
import com.remmy.registrymanager.exception.BusinessException;
import com.remmy.registrymanager.service.RegistryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "仓库接口", description = "镜像仓库相关接口")
@RestController
public class RegistryController {
    private static final Logger logger = LoggerFactory.getLogger(RegistryController.class);

    private final RegistryService registryService;

    public RegistryController(RegistryService registryService) {
        this.registryService = registryService;
    }

    @ApiOperation("获取仓库列表")
    @GetMapping("/registry")
    public ApiResponse<List<Registry>> listRegistry(@RequestParam(required = false, defaultValue = "0") int pageNum,
            @RequestParam(required = false, defaultValue = "0") int pageSize) {
        logger.debug("list registry...");
        List<Registry> results = registryService.listRegistry(pageSize, pageNum);
        return ApiResponse.markSuccess(results);
    }

    @ApiOperation("获取仓库配置")
    @GetMapping("/registry/{registryName}")
    public ApiResponse<Registry> getRegistry(@PathVariable String registryName) {
        logger.info("get registry by {}", registryName);
        Registry ret = registryService.getRegistryByName(registryName);
        if (ret == null) {
            throw new BusinessException("10000", registryName);
        }
        return ApiResponse.markSuccess(ret);
    }

    @ActionLogAnnotation("添加仓库")
    @ApiOperation("添加仓库")
    @PostMapping("/registry")
    public ApiResponse<Boolean> addRegistry(@Valid @RequestBody Registry registry) {
        int ret = registryService.addRegistry(registry);
        return ApiResponse.markSuccess(ret > 0);
    }

    @ActionLogAnnotation("删除仓库")
    @ApiOperation("删除仓库")
    @DeleteMapping("/registry/{name}")
    public ApiResponse<Boolean> deleteRegistry(@PathVariable String name) {
        int ret = registryService.deleteRegistry(name);
        return ApiResponse.markSuccess(ret > 0);
    }

    @ActionLogAnnotation("更新仓库配置")
    @ApiOperation("更新仓库配置")
    @PutMapping("/registry")
    public ApiResponse<Boolean> updateRegistry(@Valid @RequestBody Registry registry) {
        int ret = registryService.updateRegistry(registry);
        return ApiResponse.markSuccess(ret > 0);
    }
}
