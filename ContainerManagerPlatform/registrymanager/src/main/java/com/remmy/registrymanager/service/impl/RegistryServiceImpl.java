package com.remmy.registrymanager.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.remmy.registrymanager.entity.Registry;
import com.remmy.registrymanager.repository.mapper.RegistryDefineMapper;
import com.remmy.registrymanager.repository.model.RegistryDefine;
import com.remmy.registrymanager.repository.model.RegistryDefineExample;
import com.remmy.registrymanager.service.ImageService;
import com.remmy.registrymanager.service.RegistryService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistryServiceImpl implements RegistryService {

    private final RegistryDefineMapper registryDefineMapper;
    private final ImageService imageService;

    public RegistryServiceImpl(RegistryDefineMapper registryDefineMapper, ImageService imageService) {
        this.registryDefineMapper = registryDefineMapper;
        this.imageService = imageService;
    }

    /**
     * 仓库列表
     */
    public List<Registry> listRegistry(int pageSize, int pageNum) {
        PageHelper.orderBy("name");
        PageHelper.startPage(pageNum, pageSize, false, null, true);

        List<Registry> results = new LinkedList<>();
        List<RegistryDefine> registries = registryDefineMapper.selectByExample(null);
        for (RegistryDefine registryDefine : registries) {
            Registry registry = new Registry();
            registry.setHarborProductId(registryDefine.getHarborProductId());
            registry.setHarborProductName(registryDefine.getHarborProductName());
            registry.setHealth(registryDefine.getHealth());
            registry.setHost(registryDefine.getHost());
            registry.setName(registryDefine.getName());
            registry.setPort(registryDefine.getPort());
            registry.setProtocol(registryDefine.getScheme());
            registry.setRegistryType(registryDefine.getType());

            results.add(registry);
        }
        return results;
    }

    @CacheEvict(value = "registry", key = "#registry.name")
    public int addRegistry(Registry registry) {
        RegistryDefine registryDefine = new RegistryDefine();
        registryDefine.setName(registry.getName());
        registryDefine.setHost(registry.getHost());
        registryDefine.setPort(registry.getPort());
        registryDefine.setScheme(registry.getProtocol());
        registryDefine.setPassword(registry.getPassword());
        registryDefine.setUser(registry.getUser());
        registryDefine.setType(registry.getRegistryType());
        registryDefine.setHarborProductId(registry.getHarborProductId());
        registryDefine.setHarborProductName(registry.getHarborProductName());
        registryDefine.setCreateTime(new Date());
        registryDefine.setUpdateTime(new Date());
        return registryDefineMapper.insert(registryDefine);
    }

    @CacheEvict(value = "registry", key = "#registry.name")
    public int updateRegistry(Registry registry) {
        RegistryDefine registryDefine = new RegistryDefine();
        registryDefine.setName(registry.getName());
        registryDefine.setHost(registry.getHost());
        registryDefine.setPort(registry.getPort());
        registryDefine.setScheme(registry.getProtocol());
        registryDefine.setPassword(registry.getPassword());
        registryDefine.setUser(registry.getUser());
        registryDefine.setType(registry.getRegistryType());
        registryDefine.setHarborProductId(registry.getHarborProductId());
        registryDefine.setHarborProductName(registry.getHarborProductName());
        registryDefine.setUpdateTime(new Date());

        RegistryDefineExample example = new RegistryDefineExample();
        example.or().andNameEqualTo(registry.getName());
        return registryDefineMapper.updateByExampleSelective(registryDefine, example);
    }

    @CacheEvict(value = "registry", key = "#name")
    public int deleteRegistry(String name) {
        RegistryDefineExample example = new RegistryDefineExample();
        example.or().andNameEqualTo(name);
        int ret = registryDefineMapper.deleteByExample(example);
        imageService.deleteByRegsitryName(name);
        return ret;
    }

    @Cacheable(value = "registry", key = "#name")
    @Override
    public Registry getRegistryByName(String name) {
        RegistryDefineExample example = new RegistryDefineExample();
        example.or().andNameEqualTo(name);
        List<RegistryDefine> lRegistries = registryDefineMapper.selectByExample(example);
        if (!lRegistries.isEmpty()) {
            Registry registry = new Registry();
            RegistryDefine registryDefine = lRegistries.get(0);
            registry.setUser(registryDefine.getUser());
            registry.setHarborProductId(registryDefine.getHarborProductId());
            registry.setHarborProductName(registryDefine.getHarborProductName());
            registry.setHealth(registryDefine.getHealth());
            registry.setHost(registryDefine.getHost());
            registry.setName(registryDefine.getName());
            registry.setPassword(registryDefine.getPassword());
            registry.setPort(registryDefine.getPort());
            registry.setProtocol(registryDefine.getScheme());
            return registry;
        }
        return null;
    }
}
