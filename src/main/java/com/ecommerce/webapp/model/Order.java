package com.ecommerce.webapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int order_id;

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "total_price")
    private double total_price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {}

    public Order(int user_id, double total_price, OrderStatus status) {
        this.user_id = user_id;
        this.total_price = total_price;
        this.status = status;
    }

    public int getOrderId() {
        return order_id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public double getTotalPrice() {
        return total_price;
    }

    public void setTotalPrice(double total_price) {
        this.total_price = total_price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
