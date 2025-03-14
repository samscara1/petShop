package com.example.PetShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = "select * from articles where sku = :sku", nativeQuery = true)
    Optional<Article> findBySku(String sku);
}
