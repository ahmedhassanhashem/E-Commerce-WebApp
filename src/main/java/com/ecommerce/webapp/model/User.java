package com.ecommerce.webapp.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "user") 
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

    @Column(name = "credit_number",nullable = false, unique = true)
    private String credit_number;

    @Column(name = "phone",nullable = false, unique = true)
    private String phone;

    @Lob // this is a large object (JSON), store as TEXT
    @Column(name = "cart", columnDefinition = "TEXT")
    private String cart;

    @Lob
    @Column(name = "wishlist", columnDefinition = "TEXT")
    private String wishlist;

    public User() {}

    public User(String email, String password, String name, String address, double creditLimit, String phone, String credit_number) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.creditLimit = creditLimit;
        this.credit_number = credit_number;
        this.phone = phone;
        this.cart = "";
        this.wishlist = "";
    }

    public int getUserId() { return userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getCreditLimit() { return creditLimit; }
    public void setCreditLimit(double creditLimit) { this.creditLimit = creditLimit; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCart() { return cart; }    
    public void setCart(String cart) { this.cart = cart; }  

    public String getWishlist() { return wishlist; }
    public void setWishlist(String wishlist) { this.wishlist = wishlist; }

    public String getCredit_number() { return credit_number; }
    public void setCredit_number(String credit_number) { this.credit_number = credit_number;}
}
