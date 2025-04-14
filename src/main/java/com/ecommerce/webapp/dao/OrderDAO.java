package com.ecommerce.webapp.dao;

import com.ecommerce.webapp.entities.Order;
import com.ecommerce.webapp.entities.OrderStatus;
import com.ecommerce.webapp.entities.User;
import com.ecommerce.webapp.utils.PersistenceManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class OrderDAO {

    public List<Order> findAll() {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Order> query = em.createQuery(
                "SELECT o FROM Order o ORDER BY o.orderId DESC", Order.class);
        return query.getResultList();
    }

    public Order findById(int orderId) {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Order> query = em.createQuery(
                "SELECT o FROM Order o WHERE o.orderId = :id", Order.class);
        query.setParameter("id", orderId);
        return query.getSingleResult();
    }

    public List<Order> findByUser(User user) {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Order> query = em.createQuery(
                "SELECT o FROM Order o WHERE o.user = :user ORDER BY o.orderId DESC", Order.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public boolean addOrder(Order order) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            em.persist(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateStatus(int orderId, OrderStatus status) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            Order order = em.find(Order.class, orderId);
            if(order != null) {
                order.setStatus(status);
                em.merge(order);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
