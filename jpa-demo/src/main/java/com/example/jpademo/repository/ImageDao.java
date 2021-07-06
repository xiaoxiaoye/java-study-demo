package com.example.jpademo.repository;

import java.util.List;

import com.example.jpademo.entity.ImageCache;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageDao extends JpaRepository<ImageCache, Long> {
    List<ImageCache> findByName(String name);
}