package com.example.catalog.repository;

import com.example.catalog.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final Map<String, Product> storage = new ConcurrentHashMap<>();

    @Override
    public Product save(Product product) {
        storage.put(product.getPartNumber(), product);
        return product;
    }

    @Override
    public Optional<Product> findByPartNumber(String partNumber) {
        return Optional.ofNullable(storage.get(partNumber));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }
}
