package com.ecommerce.webapp.model;

public class Product {
    private int product_Id;
    private String name;
    private String description;
    private double product_price;
    private ProductCategory category;
    private String image;
    private int stock_quantity;
    private ProductColor color;
    private ProductSize size;
    

    public Product() {}

    public Product( String name, String description, double product_price, ProductCategory category, String image, int stock_quantity , ProductColor color, ProductSize size) {
        this.name = name;
        this.description = description;
        this.product_price = product_price;
        this.category = category;
        this.image = image;
        this.stock_quantity = stock_quantity;
        this.color = color;
        this.size = size;
    }

    public int getProductId() { return product_Id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getProductPrice() { return product_price; }
    public void setProductPrice(double product_price) { this.product_price = product_price; }

    public ProductCategory getCategory() { return category; }
    public void setCategory(ProductCategory category) { this.category = category; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public int getStockQuantity() { return stock_quantity; }
    public void setStockQuantity(int stock_quantity) { this.stock_quantity = stock_quantity; }

    public ProductColor getColor() { return this.color; }
    public void setColor(ProductColor color) { this.color = color; }

    public ProductSize getSize() { return this.size; }
    public void setSize(ProductSize size) { this.size = size; }
}
