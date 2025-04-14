package com.ecommerce.webapp.dao;

import com.ecommerce.webapp.entities.Product;
import com.ecommerce.webapp.entities.ProductCategory;
import com.ecommerce.webapp.utils.PersistenceManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ProductDAO {

    public Product findById(int productId) {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.productId = :id",
                Product.class
        );
        query.setParameter("id", productId);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Product> getAllProducts() {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p",
                Product.class
        );
        return query.getResultList();
    }

    public List<Product> findByCategory(ProductCategory category) {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.category = :category",
                Product.class
        );
        query.setParameter("category", category);
        return query.getResultList();
    }

    public List<Product> findLowStockProducts(int threshold) {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.stock < :threshold",
                Product.class
        );
        query.setParameter("threshold", threshold);
        return query.getResultList();
    }

    public boolean addProduct(Product product) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            em.persist(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            em.merge(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        EntityManager em = PersistenceManager.getEntityManager();
        Product product = em.find(Product.class, productId);
        if(product != null) {
            try {
                em.remove(product);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public List<Product> searchByName(String searchTerm) {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(:term)",
                Product.class
        );
        query.setParameter("term", "%" + searchTerm + "%");
        return query.getResultList();
    }

    public long getProductCount() {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(p) FROM Product p",
                Long.class
        );
        return query.getSingleResult();
    }

    public List<Product> getProductsSortedByPrice(boolean ascending) {
        EntityManager em = PersistenceManager.getEntityManager();
        String direction = ascending ? "ASC" : "DESC";
        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p ORDER BY p.price " + direction,
                Product.class
        );
        return query.getResultList();
    }

    public boolean updateProductStock(int productId, int newStock) {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.productId = :id",
                Product.class
        );
        query.setParameter("id", productId);
        try {
            Product product = query.getSingleResult();
            product.setStock(newStock);
            em.merge(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long getProductOutOfStockCount() {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE p.stock = 0",
                Long.class
        );
        return query.getSingleResult();
    }

    public long getProductInStockCount() {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE p.stock > 0",
                Long.class
        );
        return query.getSingleResult();
    }
}