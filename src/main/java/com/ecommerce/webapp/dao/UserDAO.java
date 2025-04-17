package com.ecommerce.webapp.dao;

import java.util.List;

import com.ecommerce.webapp.entities.User;
import com.ecommerce.webapp.utils.PersistenceManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UserDAO {

    public User findByEmail(String email) {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<User> query = em.createQuery(
                "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.orders WHERE u.email = :email",
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

            // Make sure the user is managed by the current persistence context
            if (!em.contains(user)) {
                // Get a reference to load the user or merge it
                User managedUser = em.find(User.class, user.getUserId());
                if (managedUser == null) {
                    // If user doesn't exist in database, merge it
                    user = em.merge(user);
                } else {
                    // Update properties of the managed entity
                    managedUser.setEmail(user.getEmail());
                    managedUser.setPassword(user.getPassword());
                    managedUser.setName(user.getName());
                    managedUser.setAddress(user.getAddress());
                    managedUser.setCreditBalance(user.getCreditBalance());
                    managedUser.setPhone(user.getPhone());
                    // We're using the managed entity
                    user = managedUser;
                }
            }
            // At this point, user is managed and changes will be persisted
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registerUser(User user) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            em.persist(user);
            return true;
        } catch (Exception e) {
            System.out.println("\n\n\n\n"+ e.getMessage() +"\n\n\n\n");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePassword(String email, String newPassword) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email",
                    User.class
            );
            query.setParameter("email", email);
            User user = query.getSingleResult();
            user.setPassword(newPassword);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<User> findAll() {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<User> query = em.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    public long getUsersCount() {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(u) FROM User u",
                Long.class
        );
        return query.getSingleResult();
    }
}