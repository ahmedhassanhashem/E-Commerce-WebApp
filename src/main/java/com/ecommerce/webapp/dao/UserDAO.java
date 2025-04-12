package com.ecommerce.webapp.dao;

import com.ecommerce.webapp.entities.User;
import com.ecommerce.webapp.utils.PersistenceManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UserDAO {

    public User findByEmail(String email) {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email",
                User.class
        );
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean validate(String email, String password) {
        User user = this.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    public boolean updateUser(User user) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            em.merge(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean registerUser(User user) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            em.persist(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updatePassword(String email, String newPassword) {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email",
                User.class
        );
        query.setParameter("email", email);
        try {
            User user = query.getSingleResult();
            user.setPassword(newPassword);
            em.merge(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}