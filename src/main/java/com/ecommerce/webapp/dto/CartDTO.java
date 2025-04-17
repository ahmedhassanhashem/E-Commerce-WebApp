package com.ecommerce.webapp.dto;

import java.util.List;

public class CartDTO {

    private int cartId;
    private List<CartItemDTO> items;
    private double totalPrice;

    // Getters and setters
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    public List<CartItemDTO> getItems() { return items; }
    public void setItems(List<CartItemDTO> items) { this.items = items; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }


}
