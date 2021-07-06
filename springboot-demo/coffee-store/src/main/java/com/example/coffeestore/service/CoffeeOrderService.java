package com.example.coffeestore.service;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.coffeestore.model.Coffee;
import com.example.coffeestore.model.CoffeeOrder;
import com.example.coffeestore.model.OrderState;
import com.example.coffeestore.repository.CoffeeOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CoffeeOrderService {
    @Autowired
    CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrder createOrder(String customer, Coffee... coffees) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffees)))
                .state(OrderState.INIT)
                .build();

        CoffeeOrder saved =  coffeeOrderRepository.save(order);
        log.info("Updated order: {}", saved);
        return order;
    }

    public boolean updateState(CoffeeOrder order, OrderState state) {
        if(state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong order state:{},{}", state, order.getState());
            return false;
        }

        order.setState(state);
        coffeeOrderRepository.save(order);
        log.info("Updated order: {}", order);
        return true;
    }
}