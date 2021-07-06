package com.remmy.registrymanager.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.pagehelper.PageHelper;
import com.remmy.registrymanager.entity.Image;
import com.remmy.registrymanager.repository.mapper.ImageCacheMapper;
import com.remmy.registrymanager.repository.model.ImageCache;
import com.remmy.registrymanager.repository.model.ImageCacheExample;
import com.remmy.registrymanager.service.ImageService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    private final ImageCacheMapper mapper;

    public ImageServiceImpl(ImageCacheMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int addImage(Image image) {
        ImageCache imageCache = convertImageToImageCache(image);
        imageCache.setRefreshTime(new Date());
        return mapper.insert(imageCache);
    }

    @Override
    public int deleteByImageID(String imageId) {
        return 0;
    }

    @Override
    public List<Image> listImages(String registryName, int pageSize, int pageNum) {
        PageHelper.orderBy("name");
        PageHelper.startPage(pageNum, pageSize, false, null, true);

        List<Image> results = new LinkedList<>();
        ImageCacheExample example = new ImageCacheExample();
        example.or().andRegistryNameEqualTo(registryName);
        List<ImageCache> records = mapper.selectByExample(example);
        for (ImageCache imageCache : records) {
            Image image = convertImageCacheToImage(imageCache);
            results.add(image);
        }
        return results;
    }

    @Override
    public int updateImage(Image image) {
        ImageCache imageCache = convertImageToImageCache(image);
        imageCache.setRefreshTime(new Date());

        ImageCacheExample example = new ImageCacheExample();
        example.or().andNameEqualTo(image.getName()).andTagEqualTo(image.getName())
                .andRegistryNameEqualTo(image.getRegistryName());
        return mapper.updateByExample(imageCache, example);
    }

    @Override
    public List<Image> queryByImageId(String imageId) {
        List<Image> results = new LinkedList<>();
        ImageCacheExample example = new ImageCacheExample();
        example.createCriteria().andImageIdEqualTo(imageId);
        List<ImageCache> records = mapper.selectByExample(example);
        for (ImageCache imageCache : records) {
            Image image = convertImageCacheToImage(imageCache);
            results.add(image);
        }
        return results;
    }

    private Image convertImageCacheToImage(ImageCache imageCache) {
        Image image = new Image();
        image.setId(imageCache.getId());
        image.setImageId(imageCache.getImageId());
        image.setName(imageCache.getName());
        image.setRegistryAddress(imageCache.getRegistryAddress());
        image.setRegistryName(imageCache.getRegistryName());
        image.setSize(imageCache.getSize());
        image.setTag(imageCache.getTag());

        return image;
    }

    private ImageCache convertImageToImageCache(Image image) {
        ImageCache imageCache = new ImageCache();
        imageCache.setImageId(image.getImageId());
        imageCache.setName(image.getName());
        imageCache.setRegistryAddress(image.getRegistryAddress());
        imageCache.setRegistryName(image.getRegistryName());
        imageCache.setSize(image.getSize());
        imageCache.setTag(image.getTag());
        return imageCache;
    }

    @Cacheable(value = "image", key = "#example.name")
    @Override
    public List<Image> queryByExample(Image example) {
        ImageCacheExample exampleQuery = new ImageCacheExample();
        exampleQuery.or().andNameEqualTo(example.getName()).andTagEqualTo(example.getTag())
                .andRegistryNameEqualTo(example.getRegistryName());
        List<ImageCache> records = mapper.selectByExample(exampleQuery);
        return records.stream().map(this::convertImageCacheToImage).collect(Collectors.toList());
    }

    @Override
    public int deleteByRegsitryName(String registryName) {
        ImageCacheExample example = new ImageCacheExample();
        example.or().andRegistryNameEqualTo(registryName);
        return mapper.deleteByExample(example);
    }
}
