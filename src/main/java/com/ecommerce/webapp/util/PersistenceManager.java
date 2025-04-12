package com.ecommerce.webapp.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager {
    private static volatile PersistenceManager instance;
    private final EntityManagerFactory emf;

    private PersistenceManager() {
        try {
            emf = Persistence.createEntityManagerFactory("webapp");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize EntityManagerFactory", e);
        }
    }

    // Double-checked locking pattern
    public static PersistenceManager getInstance() {
        if (instance == null) {
            synchronized (PersistenceManager.class) {
                if (instance == null) {
                    instance = new PersistenceManager();
                }
            }
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }

    }
}