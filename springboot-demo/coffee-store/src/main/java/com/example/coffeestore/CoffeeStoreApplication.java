package com.example.coffeestore;

import java.util.Optional;

import com.example.coffeestore.model.Coffee;
import com.example.coffeestore.model.CoffeeOrder;
import com.example.coffeestore.model.OrderState;
import com.example.coffeestore.repository.CoffeeRepository;
import com.example.coffeestore.service.CoffeeOrderService;
import com.example.coffeestore.service.CoffeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class CoffeeStoreApplication  implements ApplicationRunner{
	@Autowired
	private CoffeeRepository coffeeRepository;

	@Autowired
	private CoffeeService coffeeService;

	@Autowired
	private CoffeeOrderService coffeeOrderService;

	public static void main(String[] args) {
		SpringApplication.run(CoffeeStoreApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("All coffees:{}", coffeeRepository.findAll());

		Optional<Coffee> latte = coffeeService.findOneCoffee("latte");
		if(latte.isPresent()){
			CoffeeOrder order = coffeeOrderService.createOrder("Li Lei", latte.get());
			log.info("Update INIT to PAID: {}", coffeeOrderService.updateState(order, OrderState.PAID));
			log.info("Update PAID to INIT: {}", coffeeOrderService.updateState(order, OrderState.INIT));
		}
	}
}
