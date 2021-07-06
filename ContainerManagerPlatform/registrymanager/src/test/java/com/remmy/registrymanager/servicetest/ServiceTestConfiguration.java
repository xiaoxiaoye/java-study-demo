package com.remmy.registrymanager.servicetest;

import com.remmy.registrymanager.service.ImageService;
import com.remmy.registrymanager.tasks.ImageCacheJob;
import com.remmy.registrymanager.tasks.RegistryJob;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
    ImageCacheJob.class, RegistryJob.class
})})
public class ServiceTestConfiguration {
    // @Bean
    // @Primary
    // public ImageService imageService(){
    //     return Mockito.mock(ImageService.class);
    // }


    @Bean
    @Primary
    public RegistryJob mRegistryJob(){
        return Mockito.mock(RegistryJob.class);
    }


    @Bean
    @Primary
    public ImageCacheJob mImageCacheJob(){
        return Mockito.mock(ImageCacheJob.class);
    }
}
