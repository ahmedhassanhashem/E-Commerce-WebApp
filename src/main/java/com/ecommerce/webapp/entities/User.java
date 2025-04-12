package com.ecommerce.webapp.entities;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders; // One User can have many Orders

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(name = "credit_limit", nullable = false, precision = 19, scale = 2)
    @Min(0) // to ensure credit limit isn't negative
    private double creditLimit;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Wishlist wishlist;

    public User() {
        this.orders = new ArrayList<>();
        this.cart = new Cart(this);
        this.wishlist = new Wishlist(this);
    }

    public User(String email, String password, String name, String address, double creditLimit, String phone, Cart cart , Wishlist wishlist) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.creditLimit = creditLimit;
        this.phone = phone;
        this.cart = new Cart(this);
        this.wishlist = new Wishlist(this);
    }
    // Getter for orders relationship
    public List<Order> getOrders() {
        return orders;
    }

    //  to manage the relationship
    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        cart.setUser(this);
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
        wishlist.setUser(this);
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setOrders(List<Order> arrayList) {
        this.orders = arrayList;
    }
}
