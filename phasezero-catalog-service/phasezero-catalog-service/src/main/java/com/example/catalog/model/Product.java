package com.example.catalog.model;

import java.time.Instant;
import java.util.UUID;

public class Product {
    private UUID id;
    private String partNumber;
    private String partName;
    private String category;
    private double price;
    private int stock;
    private Instant createdAt;

    public Product() { }

    public Product(String partNumber, String partName, String category, double price, int stock) {
        this.id = UUID.randomUUID();
        this.partNumber = partNumber;
        this.partName = partName;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.createdAt = Instant.now();
    }

    public UUID getId()
        { return id; }
    public void setId(UUID id)
    	{ this.id = id; }
    public String getPartNumber() 
    	{ return partNumber; }
    public void setPartNumber(String partNumber)
    	{ this.partNumber = partNumber; }
    public String getPartName() 
    	{ return partName; }
    public void setPartName(String partName) 
    	{ this.partName = partName; }
    public String getCategory()
    	{ return category; }
    public void setCategory(String category)
    	{ this.category = category; }
    public double getPrice() 
    	{ return price; }
    public void setPrice(double price)
    	{ this.price = price; }
    public int getStock() 
    	{ return stock; }
    public void setStock(int stock) 
    	{ this.stock = stock; }
    public Instant getCreatedAt() 
    	{ return createdAt; }
    public void setCreatedAt(Instant createdAt)
    	{ this.createdAt = createdAt; }
}
