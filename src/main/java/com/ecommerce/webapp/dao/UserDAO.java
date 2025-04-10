package com.ecommerce.webapp.dao;

import com.ecommerce.webapp.entities.User;
import com.ecommerce.webapp.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UserDAO {
    
    public User findByEmail(String email) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        User user = null;
        
        try {
            entityManager.getTransaction().begin();
            
            TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            
            try {
                user = query.getSingleResult();
            } catch (NoResultException e) {
                // User not found, return null
            }
            
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        
        return user;
    }
    
    public boolean validate(String email, String password) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        User user = null;
        
        try {
            entityManager.getTransaction().begin();
            
            TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            
            try {
                user = query.getSingleResult();
            } catch (NoResultException e) {
                // User not found, will return false
            }
            
            entityManager.getTransaction().commit();
            
            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        
        return false;
    }

    public boolean updateUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }
}