package com.remmy.registrymanager.servicetest;

import com.remmy.registrymanager.service.ImageService;
import com.remmy.registrymanager.service.RegistryService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RegistryServiceTest {
    @Autowired
    private RegistryService registryService;

    @Autowired
    private ImageService imageService;

    @Test
    public void registryServiceTransactionTest(){
        String registryName = "vos";
        Mockito.when(imageService.deleteByRegsitryName(registryName)).thenThrow(new RuntimeException("example runtime exception"));
        int ret = registryService.deleteRegistry(registryName);
        Assertions.assertEquals(1, ret);
    }
}
