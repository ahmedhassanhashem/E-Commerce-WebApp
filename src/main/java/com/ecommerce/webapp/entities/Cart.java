package com.ecommerce.webapp.entities;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"user", "items"})
@EqualsAndHashCode(exclude = {"user", "items"})
// @Data makes bug with lazy init 
//@Data
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @Column(name = "cart_id")
    private int cartId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cart_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @Transient
    public double getTotalPrice() {
        return items.stream()
                .map(item -> item.getProduct().getPrice() * item.getQuantity())
                .reduce(0.0, Double::sum);
    }
}