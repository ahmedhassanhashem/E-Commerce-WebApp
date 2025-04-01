package com.ecommerce.webapp.model;

public class Order {
    private int order_Id;
    private int user_Id;
    private double total_Price;
    private OrderStatus status;

    public Order() {}

    public Order(int order_Id, int user_Id, double total_Price, OrderStatus status) {
        this.order_Id = order_Id;
        this.user_Id = user_Id;
        this.total_Price = total_Price;
        this.status = status;
    }

    public int getOrderId() { return order_Id; }
    public void setOrderId(int order_Id) { this.order_Id = order_Id; }

    public int getUserId() { return user_Id; }
    public void setUserId(int user_Id) { this.user_Id = user_Id; }

    public double getTotalPrice() { return total_Price; }
    public void setTotalPrice(double total_Price) { this.total_Price = total_Price; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
