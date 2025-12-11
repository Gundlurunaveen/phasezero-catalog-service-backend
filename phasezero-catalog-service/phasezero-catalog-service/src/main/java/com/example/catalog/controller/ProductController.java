package com.example.catalog.controller;

import com.example.catalog.dto.ProductRequest;
import com.example.catalog.model.Product;
import com.example.catalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService svc;

    public ProductController(ProductService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductRequest req) {
        Product created = svc.addProduct(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAll() {
        return ResponseEntity.ok(svc.listAll());
    }
    

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam("name") String name) {
        return ResponseEntity.ok(svc.searchByName(name));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filter(@RequestParam("category") String category) {
        return ResponseEntity.ok(svc.filterByCategory(category));
    }

    @GetMapping("/sort/price")
    public ResponseEntity<List<Product>> sortByPrice() {
        return ResponseEntity.ok(svc.sortByPriceAsc());
    }

    @GetMapping("/inventory/value")
    public ResponseEntity<?> inventoryValue() {
        double value = svc.totalInventoryValue();
        return ResponseEntity.ok(new InventoryValueResponse(value));
    }

    static class InventoryValueResponse {
        private final double totalValue;
        public InventoryValueResponse(double totalValue) { this.totalValue = totalValue; }
        public double getTotalValue() { return totalValue; }
    }
}
