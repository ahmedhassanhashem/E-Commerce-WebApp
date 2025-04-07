package com.ecommerce.webapp.model;

public class Product {
    private int product_id;
    private String name;
    private String description;
    private double price;
    private ProductCategory category;
    private String image;
    private int stock;


    public Product() {}

    public Product( String name, String description, double price, ProductCategory category, String image, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
        this.stock = stock;
    }

    public int getProductId() { return product_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setProductPrice(double price) { this.price = price; }

    public ProductCategory getCategory() { return category; }
    public void setCategory(ProductCategory category) { this.category = category; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

}
