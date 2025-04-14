package com.ecommerce.webapp.test;

import com.ecommerce.webapp.dao.OrderDAO;
import com.ecommerce.webapp.dao.ProductDAO;
import com.ecommerce.webapp.dao.UserDAO;
import com.ecommerce.webapp.entities.*;
import com.ecommerce.webapp.utils.PersistenceManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Random;

public class OrderServletTest {

    private static OrderDAO orderDAO = new OrderDAO();
    private static UserDAO userDAO = new UserDAO();
    private static ProductDAO productDAO = new ProductDAO();
    private static Random random = new Random();
    
    public static void main(String[] args) {
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            System.out.println("Creating test orders...");
            
            List<User> users = userDAO.findAll();
            if (users.isEmpty()) {
                System.out.println("No users found in database. Please add users first.");
                return;
            }
            
            List<Product> products = productDAO.getAllProducts();
            if (products.isEmpty()) {
                System.out.println("No products found in database. Please add products first.");
                return;
            }
            
            createTestOrders(em, users, products);
            tx.commit();
            
            System.out.println("\n==== TESTING GetOrders SERVLET LOGIC ====");
            testOrderFiltering();
            
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }
    
    private static void createTestOrders(EntityManager em, List<User> users, List<Product> products) {
        OrderStatus[] statuses = OrderStatus.values();
        
        for (int i = 0; i < 5; i++) {
            User user = users.get(random.nextInt(users.size()));
            
            Order order = new Order();
            order.setUser(user);
            order.setStatus(statuses[random.nextInt(statuses.length)]);
            
            int itemCount = 1 + random.nextInt(3);
            double totalPrice = 0;
            
            for (int j = 0; j < itemCount; j++) {
                Product product = products.get(random.nextInt(products.size()));
                
                OrderItem item = new OrderItem();
                item.setOrder(order);
                item.setProduct(product);
                item.setQuantity(1 + random.nextInt(3));
                item.setItemPrice(product.getPrice() * item.getQuantity());
                
                order.getItems().add(item);
                totalPrice += item.getItemPrice();
            }
            
            order.setTotalPrice(totalPrice);
            
            orderDAO.addOrder(order);
            System.out.println("Created order #" + order.getOrderId() + " for " + user.getEmail() + " with status " + order.getStatus());
        }
    }
    
    private static void testOrderFiltering() {
        List<Order> allOrders = orderDAO.findAll();
        System.out.println("\n--- All Orders (" + allOrders.size() + ") ---");
        printOrders(allOrders);
        
        for (OrderStatus status : OrderStatus.values()) {
            List<Order> filteredByStatus = allOrders.stream()
                .filter(o -> o.getStatus() == status)
                .toList();
            
            System.out.println("\n--- Orders with status " + status + " (" + filteredByStatus.size() + ") ---");
            printOrders(filteredByStatus);
        }
        
        if (!allOrders.isEmpty()) {
            int sampleOrderId = allOrders.get(0).getOrderId();
            String searchTerm = String.valueOf(sampleOrderId);
            
            List<Order> filteredById = allOrders.stream()
                .filter(o -> String.valueOf(o.getOrderId()).contains(searchTerm))
                .toList();
            
            System.out.println("\n--- Orders with ID containing '" + searchTerm + "' (" + filteredById.size() + ") ---");
            printOrders(filteredById);
        }
        
        if (!allOrders.isEmpty()) {
            String sampleEmail = allOrders.get(0).getUser().getEmail();
            String searchTerm = sampleEmail.split("@")[0];
            
            List<Order> filteredByEmail = allOrders.stream()
                .filter(o -> o.getUser().getEmail().contains(searchTerm))
                .toList();
            
            System.out.println("\n--- Orders with email containing '" + searchTerm + "' (" + filteredByEmail.size() + ") ---");
            printOrders(filteredByEmail);
        }
    }
    
    private static void printOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        
        System.out.println(String.format("%-5s | %-20s | %-10s | %-10s | %-10s", 
                "ID", "Customer", "Items", "Total", "Status"));
        System.out.println("-".repeat(65));
        
        for (Order order : orders) {
            System.out.println(String.format("%-5d | %-20s | %-10d | $%-9.2f | %-10s", 
                    order.getOrderId(),
                    order.getUser().getEmail(),
                    order.getItems().size(),
                    order.getTotalPrice(),
                    order.getStatus()));
        }
    }
}