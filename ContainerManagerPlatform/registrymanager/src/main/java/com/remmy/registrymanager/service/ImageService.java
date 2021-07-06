package com.remmy.registrymanager.service;

import java.util.List;

import com.remmy.registrymanager.entity.Image;

public interface ImageService {
    List<Image> listImages(String regisstryName, int pageSize, int pageNum);
    List<Image> queryByImageId(String imageId);
    List<Image> queryByExample(Image example);
    int deleteByImageID(String imageId);
    int deleteByRegsitryName(String registryName);
    int addImage(Image image);
    int updateImage(Image image);
}
