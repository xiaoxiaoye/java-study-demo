package com.remmy.registrymanager.tasks;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.remmy.registrymanager.client.RegistryClient;
import com.remmy.registrymanager.entity.Image;
import com.remmy.registrymanager.entity.Registry;
import com.remmy.registrymanager.service.ImageService;
import com.remmy.registrymanager.service.RegistryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class ImageCacheJob {
    private static final Logger logger = LoggerFactory.getLogger(ImageCacheJob.class);

    private final RegistryClient client;
    private final ThreadPoolTaskExecutor executor;
    private final RegistryService registryService;
    private final ImageService imageService;

    public ImageCacheJob(RegistryClient client, ThreadPoolTaskExecutor executor, RegistryService registryService,
            ImageService imageService) {
        this.client = client;
        this.executor = executor;
        this.registryService = registryService;
        this.imageService = imageService;
    }

    @Scheduled(fixedDelay = 5000)
    public void refreshImageCache() throws Exception {
        List<Registry> registries = registryService.listRegistry(0,0);
        CountDownLatch countDownLatch = new CountDownLatch(registries.size());
        for (Registry registry : registries) {
            executor.execute(() -> {
                try {
                    List<Image> images = client.listImages(registry.getName());
                    for (Image image : images) {
                        List<Image> records = imageService.queryByExample(image);
                        if(records.isEmpty()) {
                            imageService.addImage(image);
                            continue;
                        }
                        image.setId(records.get(0).getId());
                        imageService.updateImage(image);

                        logger.debug("[refresh] get image {}", image);
                    }
                } catch (Exception e) {
                    logger.error("list image {} failed, {}", registry.getName(), e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        logger.debug("refresh images finish");
    }
}
