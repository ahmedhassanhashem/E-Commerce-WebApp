package com.ecommerce.webapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private int order_item_id;

    @Column(name = "order_id", nullable = false)
    private int order_id;

    @Column(name = "product_id", nullable = false)
    private int product_id;

    @Column(name = "order_item_quantity")
    private int order_item_quantity;

    @Column(name = "item_price")
    private double item_price;

    public OrderItem() {}

    public OrderItem(int order_id, int product_id, int order_item_quantity, double item_price) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.order_item_quantity = order_item_quantity;
        this.item_price = item_price;
    }

    public int getOrderItemId() {
        return order_item_id;
    }

    public int getOrderId() {
        return order_id;
    }

    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public int getOrderItemQuantity() {
        return order_item_quantity;
    }

    public void setOrderItemQuantity(int order_item_quantity) {
        this.order_item_quantity = order_item_quantity;
    }

    public double getItemPrice() {
        return item_price;
    }

    public void setItemPrice(double item_price) {
        this.item_price = item_price;
    }
}
