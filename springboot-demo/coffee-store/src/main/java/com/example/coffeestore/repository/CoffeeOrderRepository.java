package com.example.coffeestore.repository;

import com.example.coffeestore.model.CoffeeOrder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder,Long>{
    
}