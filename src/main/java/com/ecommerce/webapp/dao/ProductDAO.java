package com.ecommerce.webapp.dao;

import com.ecommerce.webapp.entities.Product;
import com.ecommerce.webapp.entities.ProductCategory;
import com.ecommerce.webapp.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Find product by ID
    public Product findById(int productId) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Product product = null;

        try {
            entityManager.getTransaction().begin();

            product = entityManager.find(Product.class, productId);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return product;
    }

    // Get all products
    public List<Product> getAllProducts() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        List<Product> products = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            TypedQuery<Product> query = entityManager.createQuery(
                    "SELECT p FROM Product p ORDER BY p.product_id", Product.class);
            products = query.getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return products;
    }

    // Find products by category
    public List<Product> findByCategory(ProductCategory category) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        List<Product> products = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            TypedQuery<Product> query = entityManager.createQuery(
                    "SELECT p FROM Product p WHERE p.category = :category", Product.class);
            query.setParameter("category", category);
            products = query.getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return products;
    }

    // Find products with stock less than a certain threshold
    public List<Product> findLowStockProducts(int threshold) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        List<Product> products = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            TypedQuery<Product> query = entityManager.createQuery(
                    "SELECT p FROM Product p WHERE p.stock < :threshold ORDER BY p.stock", Product.class);
            query.setParameter("threshold", threshold);
            products = query.getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return products;
    }

    // Add new product
    public boolean addProduct(Product product) {
        EntityManager entityManager = HibernateUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(product);

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

    // Update existing product
    public boolean updateProduct(Product product) {
        EntityManager entityManager = HibernateUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            Product existingProduct = entityManager.find(Product.class, product.getProductId());
            if (existingProduct == null) {
                return false;
            }

            // Update fields
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setImage(product.getImage());
            existingProduct.setStock(product.getStock());

            entityManager.merge(existingProduct);

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

    // Delete product
    public boolean deleteProduct(int productId) {
        EntityManager entityManager = HibernateUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            Product product = entityManager.find(Product.class, productId);
            if (product == null) {
                return false;
            }

            entityManager.remove(product);

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

    // Search products by name
    public List<Product> searchByName(String searchTerm) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        List<Product> products = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            TypedQuery<Product> query = entityManager.createQuery(
                    "SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(:searchTerm)", Product.class);
            query.setParameter("searchTerm", "%" + searchTerm + "%");
            products = query.getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return products;
    }

    // Get product count
    public long getProductCount() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        long count = 0;

        try {
            entityManager.getTransaction().begin();

            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(p) FROM Product p", Long.class);
            count = query.getSingleResult();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return count;
    }

    // Get products sorted by price (ascending or descending)
    public List<Product> getProductsSortedByPrice(boolean ascending) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        List<Product> products = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            String direction = ascending ? "ASC" : "DESC";
            TypedQuery<Product> query = entityManager.createQuery(
                    "SELECT p FROM Product p ORDER BY p.price " + direction, Product.class);
            products = query.getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return products;
    }

    // Update product stock
    public boolean updateProductStock(int productId, int newStock) {
        EntityManager entityManager = HibernateUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            int updated = entityManager.createQuery(
                            "UPDATE Product p SET p.stock = :newStock WHERE p.product_id = :productId")
                    .setParameter("newStock", newStock)
                    .setParameter("productId", productId)
                    .executeUpdate();

            entityManager.getTransaction().commit();
            return updated > 0;
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
}