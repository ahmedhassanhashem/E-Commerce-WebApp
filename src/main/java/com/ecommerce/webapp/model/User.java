package com.ecommerce.webapp.model;

public class User {
    private int userId;
    private String email;
    private String password;
    private String name;
    private String address;
    private double creditLimit;
    private String phone;
    private String cart; //Json String
    private String wishlist;//Json String

    // Constructors
    public User() {}

    public User(String email, String password, String name, String address, double creditLimit, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.creditLimit = creditLimit;
        this.phone = phone;
        this.cart = "";
        this.wishlist = "";
    }

    // Getters and Setters
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
    public void setWishlist(String wishlist) { this.wishlist = wishlist;}
}
