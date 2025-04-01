package com.ecommerce.webapp.model;

public class OrderItem {
    private int order_Item_Id;
    private int order_Id;
    private int product_Id;
    private int order_Item_Quantity;
    private double item_Price;

    public OrderItem() {}

    public OrderItem(int order_Item_Id, int order_Id, int product_Id, int order_Item_Quantity, double item_Price) {
        this.order_Item_Id = order_Item_Id;
        this.order_Id = order_Id;
        this.product_Id = product_Id;
        this.order_Item_Quantity = order_Item_Quantity;
        this.item_Price = item_Price;
    }

    public int get_Order_ItemId() { return order_Item_Id; }
    public void set_Order_ItemId(int order_Item_Id) { this.order_Item_Id = order_Item_Id; }

    public int getOrderId() { return order_Id; }
    public void setOrderId(int order_Id) { this.order_Id = order_Id; }

    public int getProductId() { return product_Id; }
    public void setProductId(int product_Id) { this.product_Id = product_Id; }

    public int getOrderItemQuantity() { return order_Item_Quantity; }
    public void setOrderItemQuantity(int order_Item_Quantity) { this.order_Item_Quantity = order_Item_Quantity; }

    public double getItemPrice() { return item_Price; }
    public void setItemPrice(double item_Price) { this.item_Price = item_Price; }
}
