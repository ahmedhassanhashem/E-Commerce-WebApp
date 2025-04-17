package com.ecommerce.webapp.dto;

import java.util.List;

public class CartItemDTO {

    private int id;
    private ProductDTO product;
    private int quantity;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public ProductDTO getProduct() { return product; }
    public void setProduct(ProductDTO product) { this.product = product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}


