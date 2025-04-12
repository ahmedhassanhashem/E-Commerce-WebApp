package com.ecommerce.webapp.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;// Many Orders can belong to one User

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;// One Order can have many OrderItems

    private double total_price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {
        this.orderItems = new ArrayList<>();
    }

    public Order(User user, double total_price, OrderStatus status) {
        this.user = user;
        this.total_price = total_price;
        this.status = status;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    //  to manage the relationship
    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
        item.setOrder(this);
    }

    /*======= for testing purposes ======*/
    public void setOrderId(int order_id){
        this.order_id = order_id;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /*=====================================*/


    public int getOrderId() {
        return order_id;
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
