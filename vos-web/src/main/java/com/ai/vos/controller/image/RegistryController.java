package com.ai.vos.controller.image;

import com.ai.vos.domain.common.ApiResponse;
import com.ai.vos.service.RegistryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ai.vos.domain.image.RegistryList.Registry;

/**
 * @program: vos-web
 * 镜像仓库管理类
 * @author: yejx
 * @create: 2020-09-23 14:55
 */
@Api("Registry")
@RestController
public class RegistryController {
    private static final Logger logger = LoggerFactory.getLogger(RegistryController.class);

    private final RegistryService registryService;

    public RegistryController(RegistryService registryService) {
        this.registryService = registryService;
    }

    @ApiOperation(value = "查询仓库列表", notes = "查询仓库列表")
    @GetMapping("/registry")
    public ApiResponse<List<Registry>> listRegistry() {
        List<Registry> registries = registryService.listRegistry();
        logger.info("info get total {} registry", registries.size());
        return ApiResponse.markSuccess(registries);
    }
}
