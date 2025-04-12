package com.ecommerce.webapp.utils;

import jakarta.persistence.EntityManager;

public class PersistenceManager {
    private static final ThreadLocal<EntityManager> ENTITY_MANAGER = new ThreadLocal<>();

    public static void setEntityManager(EntityManager em) {
        ENTITY_MANAGER.set(em);
    }

    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER.get();
    }

    public static void clearEntityManager() {
        ENTITY_MANAGER.remove();
    }
}