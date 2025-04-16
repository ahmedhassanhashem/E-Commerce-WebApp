package com.ecommerce.webapp.dao;

import java.util.List;

import com.ecommerce.webapp.entities.User;
import com.ecommerce.webapp.utils.PersistenceManager;
import jakarta.persistence.*;

public class UserDAO {

//    public User findByEmail(String email) {
//        EntityManager em = PersistenceManager.getEntityManager();
//        TypedQuery<User> query = em.createQuery(
//                "SELECT u FROM User u WHERE u.email = :email",
//                User.class
//        );
//        query.setParameter("email", email);
//        try {
//            return query.getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }

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

//    public boolean updateUser(User user) {
//        try {
//            EntityManager em = PersistenceManager.getEntityManager();
//            em.merge(user);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public boolean updateUserProfile(int userId, String name, String email, String phone, String address) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();

            // First, check if the email or phone is already in use by a different user
            TypedQuery<Long> duplicateQuery = em.createQuery(
                    "SELECT COUNT(u) FROM User u WHERE (u.email = :email OR u.phone = :phone) AND u.userId != :userId",
                    Long.class);
            duplicateQuery.setParameter("email", email);
            duplicateQuery.setParameter("phone", phone);
            duplicateQuery.setParameter("userId", userId);

            Long duplicateCount = duplicateQuery.getSingleResult();

            if (duplicateCount > 0) {
                return false;
            }

            // Now perform the update
            int updated = em.createQuery(
                            "UPDATE User u SET u.name = :name, u.email = :email, " +
                                    "u.phone = :phone, u.address = :address " +
                                    "WHERE u.userId = :userId")
                    .setParameter("name", name)
                    .setParameter("email", email)
                    .setParameter("phone", phone)
                    .setParameter("address", address)
                    .setParameter("userId", userId)
                    .executeUpdate();

            return updated > 0;
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
            System.out.println("\n\n\n\n" + e.getMessage() + "\n\n\n\n");
            e.printStackTrace();
            return false;
        }
    }

//    public boolean updatePassword(String email, String newPassword) {
//        EntityManager em = PersistenceManager.getEntityManager();
//        TypedQuery<User> query = em.createQuery(
//                "SELECT u FROM User u WHERE u.email = :email",
//                User.class
//        );
//        query.setParameter("email", email);
//        try {
//            User user = query.getSingleResult();
//            user.setPassword(newPassword);
//            em.merge(user);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }


    public boolean updatePassword(String email, String newPassword) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();

            int updated = em.createQuery(
                            "UPDATE User u SET u.password = :newPassword WHERE u.email = :email")
                    .setParameter("newPassword", newPassword)
                    .setParameter("email", email)
                    .executeUpdate();

            return updated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> findAll() {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<User> query = em.createQuery("FROM User", User.class);
        return query.getResultList(); // returns empty list if no results

    }


    public long getUsersCount() {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(u) FROM User u",
                Long.class
        );
        return query.getSingleResult();
    }

    public User findById(int userId) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            return em.find(User.class, userId);
        } catch (Exception e) {
            return null;
        }
    }




    public boolean emailExists(String email) {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.email = :email",
                Long.class
        );
        query.setParameter("email", email);
        return query.getSingleResult() > 0;
    }

    public boolean updateUserBalance(int userId, double newBalance) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.userId = :userId",
                    User.class
            );
            query.setParameter("userId", userId);

            User user = query.getSingleResult();
            user.setCreditBalance(newBalance);
            em.merge(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}