package com.example.eurekaclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class EurekaClient1Application {
    @GetMapping("/hello")
    public String hello() {
        return "hello! I'm eureka-client1";
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient1Application.class, args);
    }

}
