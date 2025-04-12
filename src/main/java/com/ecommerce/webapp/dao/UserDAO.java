package com.ecommerce.webapp.dao;

import com.ecommerce.webapp.entities.User;
import com.ecommerce.webapp.util.PersistenceManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;


public class UserDAO {
    
<<<<<<< HEAD
    public User findByEmail(String email) {
      //  EntityManager entityManager = PersistenceManager.getEntityManager();
EntityManager entityManager = null;
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
      //  EntityManager entityManager = PersistenceManager.getEntityManager();
EntityManager entityManager = null;
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
      //  EntityManager entityManager = PersistenceManager.getEntityManager();
EntityManager entityManager = null;

        try {
            entityManager.getTransaction().begin();

            User existingUser = entityManager.find(User.class, user.getUserId());
            if (existingUser == null) {
                return false;
            }

            // Update user fields
            existingUser.setEmail(user.getEmail());
            existingUser.setName(user.getName());
            existingUser.setAddress(user.getAddress());
            existingUser.setPhone(user.getPhone());
            existingUser.setCreditLimit(user.getCreditLimit());
            existingUser.setCredit_number(user.getCredit_number());
            // Don't update password unless it has changed
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                existingUser.setPassword(user.getPassword());
            }

            entityManager.merge(existingUser);

            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    public boolean RegisterUser(User user) {
      //  EntityManager entityManager = PersistenceManager.getEntityManager();
EntityManager entityManager = null;

        try {
            entityManager.getTransaction().begin();

            // Check if a user with this email already exists
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
            query.setParameter("email", user.getEmail());

            Long count = query.getSingleResult();
            if (count > 0) {
                // User with this email already exists
                return false;
            }

            entityManager.persist(user);

            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }
=======
//    public User findByEmail(String email) {
////        EntityManager entityManager = PersistenceManager.getEntityManager();
//        EntityManager entityManager = null;
//
//        User user = null;
//
//        try {
//            entityManager.getTransaction().begin();
//
//            TypedQuery<User> query = entityManager.createQuery(
//                "SELECT u FROM User u WHERE u.email = :email", User.class);
//            query.setParameter("email", email);
//
//            try {
//                user = query.getSingleResult();
//            } catch (NoResultException e) {
//                // User not found, return null
//            }
//
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            if (entityManager.getTransaction().isActive()) {
//                entityManager.getTransaction().rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            entityManager.close();
//        }
//
//        return user;
//    }
//
//    public boolean validate(String email, String password) {
////        EntityManager entityManager = PersistenceManager.getEntityManager();
//        EntityManager entityManager = null;
//
//        User user = null;
//
//        try {
//            entityManager.getTransaction().begin();
//
//            TypedQuery<User> query = entityManager.createQuery(
//                "SELECT u FROM User u WHERE u.email = :email", User.class);
//            query.setParameter("email", email);
//
//            try {
//                user = query.getSingleResult();
//            } catch (NoResultException e) {
//                // User not found, will return false
//            }
//
//            entityManager.getTransaction().commit();
//
//            if (user != null && user.getPassword().equals(password)) {
//                return true;
//            }
//        } catch (Exception e) {
//            if (entityManager.getTransaction().isActive()) {
//                entityManager.getTransaction().rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            entityManager.close();
//        }
//
//        return false;
//    }
//
//    public boolean updateUser(User user) {
////        EntityManager entityManager = PersistenceManager.getEntityManager();
//        EntityManager entityManager = null;
//
//
//        try {
//            entityManager.getTransaction().begin();
//
//            User existingUser = entityManager.find(User.class, user.getUserId());
//            if (existingUser == null) {
//                return false;
//            }
//
//            // Update user fields
//            existingUser.setEmail(user.getEmail());
//            existingUser.setName(user.getName());
//            existingUser.setAddress(user.getAddress());
//            existingUser.setPhone(user.getPhone());
//            existingUser.setCreditLimit(user.getCreditLimit());
//            existingUser.setCredit_number(user.getCredit_number());
//            // Don't update password unless it has changed
//            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
//                existingUser.setPassword(user.getPassword());
//            }
//
//            entityManager.merge(existingUser);
//
//            entityManager.getTransaction().commit();
//            return true;
//        } catch (Exception e) {
//            if (entityManager.getTransaction().isActive()) {
//                entityManager.getTransaction().rollback();
//            }
//            e.printStackTrace();
//            return false;
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    public boolean RegisterUser(User user) {
////        EntityManager entityManager = PersistenceManager.getEntityManager();
//        EntityManager entityManager = null;
//
//
//        try {
//            entityManager.getTransaction().begin();
//
//            // Check if a user with this email already exists
//            TypedQuery<Long> query = entityManager.createQuery(
//                    "SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
//            query.setParameter("email", user.getEmail());
//
//            Long count = query.getSingleResult();
//            if (count > 0) {
//                // User with this email already exists
//                return false;
//            }
//
//            entityManager.persist(user);
//
//            entityManager.getTransaction().commit();
//            return true;
//        } catch (Exception e) {
//            if (entityManager.getTransaction().isActive()) {
//                entityManager.getTransaction().rollback();
//            }
//            e.printStackTrace();
//            return false;
//        } finally {
//            entityManager.close();
//        }
//    }
>>>>>>> 416492525a3bfc0eb5df197375ab3e4d34a0180f
}