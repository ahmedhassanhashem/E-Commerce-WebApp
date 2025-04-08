package com.ecommerce.webapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")  
    private int product_id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "product_price")
    private double price;

    private String image;

    @Column(name = "stock")
    private int stock;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;


    public Product() {}

    public Product(String name, String description, double product_price, ProductCategory category, String image, int stock) {
        this.name = name;
        this.description = description;
        this.price = product_price;
        this.category = category;
        this.image = image;
        this.stock = stock;
    }

    public int getProductId() {
        return product_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }
    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }


}
