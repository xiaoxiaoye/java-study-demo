package com.ai.vos.service.impl;

import com.ai.vos.common.Api;
import com.ai.vos.domain.image.RegistryList;
import com.ai.vos.service.RegistryService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.ai.vos.domain.image.RegistryList.Registry;

/**
 * @program: vos-web
 * 镜像仓库服务
 * @author: yejx
 * @create: 2020-09-23 15:16
 */

@Service
public class RegistryServiceImpl implements RegistryService {
    private final RestTemplate restTemplate;

    public RegistryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Registry> listRegistry() {
        RegistryList res = restTemplate.getForObject(Api.Registry.REGISTRY_LIST_QUERY.url(), RegistryList.class);
        if (res == null) {
            throw new RuntimeException("no registry");
        }
        return res.getRegistryList();
    }
}
