package com.example.mybatisdemo;

import com.example.mybatisdemo.mapper.CoffeeMapper;
import com.example.mybatisdemo.model.CoffeeExample;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@MapperScan("com.example.mybatisdemo.mapper")
public class DemoApplication implements ApplicationRunner {

	@Autowired
	CoffeeMapper coffeeMapper;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		CoffeeExample example = new CoffeeExample();
		coffeeMapper.selectByExampleWithRowbounds(example, new RowBounds(1,1)).forEach(c -> log.info("page 1 coffee: {}", c));
	}

}
