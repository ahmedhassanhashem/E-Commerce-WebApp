package com.ecommerce.webapp.model;

import java.util.Date;

public class Cart {
    private int cart_Id;
    private int user_Id;
    private String cart_Items; // JSON string to store cart items
    private Date createdAt;

    public Cart() {}

    public Cart(int cart_Id, int user_Id, String cart_Items, Date createdAt) {
        this.cart_Id = cart_Id;
        this.user_Id = user_Id;
        this.cart_Items = cart_Items;
        this.createdAt = createdAt;
    }

    public int getCartId() { return cart_Id; }
    public void setCartId(int cart_Id) { this.cart_Id = cart_Id; }

    public int getUserId() { return user_Id; }
    public void setUserId(int user_Id) { this.user_Id = user_Id; }

    public String getCartItems() { return cart_Items; }
    public void setCartItems(String cart_Items) { this.cart_Items = cart_Items; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
