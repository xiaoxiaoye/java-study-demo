package com.example.jpademo.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.example.jpademo.entity.RegistryDefine;
import com.example.jpademo.entity.RegistryType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class RegistryDaoTest {
    @Autowired
    private RegistryDao registryDao;

    @Test
    public void testSaveRegistry(){
        RegistryDefine registry = RegistryDefine.builder().name("test").host("127.0.0.1").port(22).type(RegistryType.V2).build();
        registry.setId(9);
        registryDao.save(registry);

        List<RegistryDefine> records = registryDao.findByName("test");
        Assertions.assertEquals(1, records.size());
    }

    @Transactional
    @Test
    public void testDeleteRegistry(){
        registryDao.deleteByName("test");
        List<RegistryDefine> records = registryDao.findByName("test");
        Assertions.assertEquals(0, records.size());
    }

    @Test
    public void testQueryRegistry(){
        List<RegistryDefine> records = registryDao.findByName("vos");
        log.info(records.get(0).toString());
    }
}
