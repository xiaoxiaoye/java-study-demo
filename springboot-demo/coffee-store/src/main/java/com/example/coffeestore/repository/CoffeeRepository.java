package com.example.coffeestore.repository;

import com.example.coffeestore.model.Coffee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long>{
    
}