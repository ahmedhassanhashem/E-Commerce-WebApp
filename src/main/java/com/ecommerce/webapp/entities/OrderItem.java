package com.ecommerce.webapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private int order_item_id;

    @Column(name = "order_item_quantity")
    private int order_item_quantity;

    @Column(name = "item_price")
    private double item_price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public OrderItem() {}

    public OrderItem(Order order, Product product, int order_item_quantity, double item_price) {
        this.order = order;
        this.product = product;
        this.order_item_quantity = order_item_quantity;
        this.item_price = item_price;
    }

    public Order getOrder() {

        return order;
    }

    public void setOrder(Order order) {

        this.order = order;
    }

    public Product getProduct() {

        return product;
    }

    public void setProduct(Product product) {

        this.product = product;
    }

    public int getOrderItemId() {
        return order_item_id;
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
