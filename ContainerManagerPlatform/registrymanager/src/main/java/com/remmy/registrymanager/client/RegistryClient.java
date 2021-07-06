package com.remmy.registrymanager.client;

import java.util.List;

import com.remmy.registrymanager.entity.Image;

public interface RegistryClient {
    List<Image> listImages(String registryName) throws Exception;
    void deleteByTag(String name, String tag);
    boolean health();
}
