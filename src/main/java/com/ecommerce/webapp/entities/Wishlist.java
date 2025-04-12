package com.ecommerce.webapp.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "wishlist_product",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> wishList_items = new HashSet<>();

    public Wishlist() {}

    public Wishlist(User user) {
        this.user = user;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getWishlistItems() {
        return wishList_items;
    }

    public void setWishlistItems(Set<Product> wishList_items) {
        this.wishList_items = wishList_items;
    }

    public void addWishlistItem(Product wishlistItem) {
        wishList_items.add(wishlistItem);
    }

    public void removeWishlistItem(Product wishList_item) {
        wishList_items.remove(wishList_item);
    }

    public void clearWishlist() {
        for (Product item : new ArrayList<>(wishList_items)) {
            removeWishlistItem(item);
        }
    }

    // Check if product is in wishlist
    public boolean containsProduct(Product product) {
        for (Product item : wishList_items) {
            if (item.getProduct().getProductId() == product.getProductId()) {
                return true;
            }
        }
        return false;
    }
}