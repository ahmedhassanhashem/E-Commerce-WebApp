package com.ecommerce.webapp.utils;

import jakarta.persistence.EntityManager;

public class PersistenceManager {
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    public static void setEntityManager(EntityManager em) {
        threadLocal.set(em);
    }

    public static EntityManager getEntityManager() {
        EntityManager em = threadLocal.get();
        if (em == null) {
            throw new IllegalStateException("No EntityManager bound to this thread. Make sure requests go through TransactionFilter.");
        }
        return em;
    }

    public static void clearEntityManager() {
        threadLocal.remove();
    }

    // Helper method to check if transaction is active
    public static boolean isTransactionActive() {
        EntityManager em = threadLocal.get();
        return em != null && em.getTransaction().isActive();
    }
}