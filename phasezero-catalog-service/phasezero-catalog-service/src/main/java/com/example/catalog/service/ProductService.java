package com.example.catalog.service;

import com.example.catalog.dto.ProductRequest;
import com.example.catalog.exception.ConflictException;
import com.example.catalog.exception.NotFoundException;
import com.example.catalog.model.Product;
import com.example.catalog.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product addProduct(ProductRequest req) {
        String partNumber = req.getPartNumber().trim();

        if (repo.findByPartNumber(partNumber).isPresent()) {
            throw new ConflictException("Product with partNumber '" + partNumber + "' already exists");
        }

        String normalizedPartName = req.getPartName().trim().toLowerCase(Locale.ROOT);

        if (req.getPrice() < 0 || req.getStock() < 0) {
            throw new IllegalArgumentException("price and stock cannot be negative");
        }

        Product p = new Product(partNumber, normalizedPartName, req.getCategory().trim(), req.getPrice(), req.getStock());
        return repo.save(p);
    }

    public List<Product> listAll() {
        return repo.findAll();
    }

    public List<Product> searchByName(String text) {
        if (text == null) return listAll();
        String q = text.toLowerCase();
        return repo.findAll().stream()
                .filter(p -> p.getPartName() != null && p.getPartName().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    public List<Product> filterByCategory(String category) {
        if (category == null) return listAll();
        String cat = category.trim();
        return repo.findAll().stream()
                .filter(p -> p.getCategory() != null && p.getCategory().equalsIgnoreCase(cat))
                .collect(Collectors.toList());
    }

    public List<Product> sortByPriceAsc() {
        return repo.findAll().stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    public double totalInventoryValue() {
        return repo.findAll().stream()
                .mapToDouble(p -> p.getPrice() * p.getStock())
                .sum();
    }

    public void clearAll() {
        repo.deleteAll();
    }

    public Product findByPartNumber(String partNumber) {
        return repo.findByPartNumber(partNumber)
                .orElseThrow(() -> new NotFoundException("Product with partNumber '" + partNumber + "' not found"));
    }
}
