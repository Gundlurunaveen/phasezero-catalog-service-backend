package com.example.catalog.repository;

import com.example.catalog.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findByPartNumber(String partNumber);
    List<Product> findAll();
    void deleteAll();
}
