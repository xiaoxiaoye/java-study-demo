package com.remmy.registrymanager.service;

import java.util.List;

import com.remmy.registrymanager.entity.Registry;

public interface RegistryService {
    List<Registry> listRegistry(int pageSize, int pageNum);
    int addRegistry(Registry registry);
    int deleteRegistry(String name);
    int updateRegistry(Registry registry);
    Registry getRegistryByName(String name);
}
