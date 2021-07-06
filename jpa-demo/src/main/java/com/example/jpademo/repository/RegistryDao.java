package com.example.jpademo.repository;

import java.util.List;

import com.example.jpademo.entity.RegistryDefine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryDao extends JpaRepository<RegistryDefine, Long> {
    List<RegistryDefine> findByName(String name);

    long deleteByName(String name);
}
