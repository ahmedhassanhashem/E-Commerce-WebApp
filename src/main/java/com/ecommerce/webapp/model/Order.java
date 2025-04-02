package com.ecommerce.webapp.model;

public class Order {
    private int order_id;
    private int user_id;
    private double total_price;
    private OrderStatus status;

    public Order() {}

    public Order( int user_id, double total_price, OrderStatus status) {
        this.user_id = user_id;
        this.total_price = total_price;
        this.status = status;
    }

    public int getOrderId() { return order_id; }

    public int getUserId() { return user_id; }
    public void setUserId(int user_id) { this.user_id = user_id; }

    public double getTotalPrice() { return total_price; }
    public void setTotalPrice(double total_price) { this.total_price = total_price; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
