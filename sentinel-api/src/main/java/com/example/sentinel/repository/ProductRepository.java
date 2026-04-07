package com.example.sentinel.repository;

import com.example.sentinel.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // O Spring traduz isso para: SELECT count(*) FROM products WHERE sku = ?
    boolean existsBySku(String sku);
}