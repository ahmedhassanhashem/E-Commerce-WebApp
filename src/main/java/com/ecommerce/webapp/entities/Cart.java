package com.ecommerce.webapp.entities;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "user_cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Calculated field, not stored in database
    @Transient
    private double totalPrice;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Map<Product, Integer> cartItems = new HashMap<>(); // Product to quantity mapping

    public Cart() {}

    public Cart(User user) {
        this.user = user;
    }

    public int getCartId() {
        return cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Map<Product,Integer> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Product,Integer> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(Product cartItem , Integer cartItemQuantity) {
        cartItems.put(cartItem,cartItemQuantity);
    }

    public void removeCartItem(Product cartItem) {
        cartItems.remove(cartItem);
    }

    // Calculate total price of all items in cart
    public double getTotalPrice() {
        totalPrice = 0;
        for (Product item : cartItems.keySet()) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}