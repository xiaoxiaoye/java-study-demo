package com.remmy.registrymanager;

import java.util.List;

import com.remmy.registrymanager.client.RegistryClient;
import com.remmy.registrymanager.entity.Image;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegistryManagerApplicationTests {
	@Autowired
	private RegistryClient client;

	@Test
	void contextLoads() {
	}

	@Test
	void listImages() throws Exception {
		Long t1 = System.currentTimeMillis();
		List<Image> images = client.listImages("vos");
		images.stream().forEach(System.out::println);
		System.out.println("listImages cost: " + (System.currentTimeMillis()-t1) + "ms");
	}
}
