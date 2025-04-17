package com.ecommerce.webapp.dao;

import com.ecommerce.webapp.entities.Order;
import com.ecommerce.webapp.entities.OrderStatus;
import com.ecommerce.webapp.entities.User;
import com.ecommerce.webapp.utils.PersistenceManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public List<Order> findAll() {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Order> query = em.createQuery(
                "SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items i LEFT JOIN FETCH i.product", Order.class);
        return query.getResultList();
    }

    public Order findById(int orderId) {
        EntityManager em = PersistenceManager.getEntityManager();
        // Use join fetch to eagerly load items and products
        TypedQuery<Order> query = em.createQuery(
                "SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items i LEFT JOIN FETCH i.product WHERE o.orderId = :id", Order.class);
        query.setParameter("id", orderId);
        return query.getSingleResult();
    }

    public List<Order> findByUser(User user) {
        EntityManager em = PersistenceManager.getEntityManager();
        return em.createQuery(
                        "SELECT DISTINCT o FROM Order o " +
                                "LEFT JOIN FETCH o.items i " +       // Fetch items
                                "LEFT JOIN FETCH i.product " +        // Fetch product
                                "WHERE o.user = :user " +
                                "ORDER BY o.orderId DESC", Order.class)
                .setParameter("user", user)
                .getResultList();
    }

    /**
     * Find orders by user specifically for display in session.
     * This version detaches the orders from persistence context to avoid circular references
     * but maintains the needed user reference for database integrity.
     */
    public List<Order> findByUserForSession(User user) {
        EntityManager em = PersistenceManager.getEntityManager();

        // First get all order IDs for this user
        List<Integer> orderIds = em.createQuery(
                        "SELECT o.orderId FROM Order o WHERE o.user.userId = :userId ORDER BY o.orderId DESC", Integer.class)
                .setParameter("userId", user.getUserId())
                .getResultList();

        // Then load each order individually with its items and products
        List<Order> orders = new ArrayList<>();
        for (Integer orderId : orderIds) {
            try {
                // Use a query that eagerly fetches both items and products in one go
                Order order = em.createQuery(
                                "SELECT DISTINCT o FROM Order o " +
                                        "LEFT JOIN FETCH o.items i " +
                                        "LEFT JOIN FETCH i.product " +
                                        "WHERE o.orderId = :orderId", Order.class)
                        .setParameter("orderId", orderId)
                        .getSingleResult();

                // IMPORTANT: Instead of setting user to null, create a lightweight user object
                // that maintains the ID reference but breaks the bi-directional relationship
                User lightUser = new User();
                lightUser.setUserId(user.getUserId());
                order.setUser(lightUser);

                // Detach the entity from persistence context to prevent accidental updates
                em.detach(order);

                orders.add(order);
            } catch (Exception e) {
                // Log error or skip this order
                System.err.println("Error loading order #" + orderId + ": " + e.getMessage());
            }
        }

        return orders;
    }

    public boolean addOrder(Order order) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();

            // Ensure order has a user reference
            if (order.getUser() == null) {
                return false;
            }

            // Make sure we're using a managed user entity
            User user = em.find(User.class, order.getUser().getUserId());
            if (user == null) {
                return false;
            }

            order.setUser(user);

            // Persist the order first
            em.persist(order);

            // Ensure each OrderItem has a reference to the persisted order
            if (order.getItems() != null) {
                order.getItems().forEach(item -> {
                    item.setOrder(order);
                    // Make sure we have a managed product entity
                    item.setProduct(em.find(item.getProduct().getClass(), item.getProduct().getProductId()));
                });
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStatus(int orderId, OrderStatus status) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            Order order = em.find(Order.class, orderId);
            if (order != null) {
                order.setStatus(status);
                em.merge(order);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long countByStatus(OrderStatus status) {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(o) FROM Order o WHERE o.status = :status", Long.class);
        query.setParameter("status", status);
        return query.getSingleResult();
    }
}