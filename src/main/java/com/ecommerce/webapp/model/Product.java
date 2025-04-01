package com.ecommerce.webapp.model;

public class Product {
    private int product_Id;
    private String name;
    private String description;
    private double productPrice;
    private ProductCategory category;
    private String image;
    private int stock_Quantity;

    public Product() {}

    public Product(int product_Id, String name, String description, double productPrice, ProductCategory category, String image, int stock_Quantity) {
        this.product_Id = product_Id;
        this.name = name;
        this.description = description;
        this.productPrice = productPrice;
        this.category = category;
        this.image = image;
        this.stock_Quantity = stock_Quantity;
    }

    public int getProductId() { return product_Id; }
    public void setProductId(int product_Id) { this.product_Id = product_Id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getProductPrice() { return productPrice; }
    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }

    public ProductCategory getCategory() { return category; }
    public void setCategory(ProductCategory category) { this.category = category; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public int getStockQuantity() { return stock_Quantity; }
    public void setStockQuantity(int stock_Quantity) { this.stock_Quantity = stock_Quantity; }
}
