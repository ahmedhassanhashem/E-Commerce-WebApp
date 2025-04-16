package com.ecommerce.webapp.dao;

import com.ecommerce.webapp.entities.Cart;
import com.ecommerce.webapp.entities.CartItem;
import com.ecommerce.webapp.entities.Product;
import com.ecommerce.webapp.entities.User;
import com.ecommerce.webapp.utils.PersistenceManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Optional;

public class CartDAO {

    public Cart getCartByUser(User user) {
        EntityManager em = PersistenceManager.getEntityManager();
        TypedQuery<Cart> query = em.createQuery(
                "SELECT c FROM Cart c WHERE c.user = :user", Cart.class);
        query.setParameter("user", user);
        return query.getSingleResult();
    }

    public boolean addItemToCart(Cart cart, Product product, int quantity) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();

            // Check if item already exists in cart
            Optional<CartItem> existingItem = cart.getItems().stream()
                    .filter(item -> item.getProduct().getProductId() == product.getProductId())
                    .findFirst();

            if(existingItem.isPresent()) {
                CartItem item = existingItem.get();
                item.setQuantity(item.getQuantity() + quantity);
                em.merge(item);
            } else {
                CartItem newItem = new CartItem();
                newItem.setCart(cart);
                newItem.setProduct(product);
                newItem.setQuantity(quantity);
                em.persist(newItem);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateItemQuantity(Long cartItemId, int newQuantity) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            CartItem item = em.find(CartItem.class, cartItemId);
            if(item != null) {
                item.setQuantity(newQuantity);
                em.merge(item);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeItemFromCart(Long cartItemId) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            em.getTransaction().begin();
            CartItem item = em.find(CartItem.class, cartItemId);
            if(item != null) {
                // Remove from both sides of the relationship
                item.getCart().getItems().remove(item);
                em.remove(item);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean clearCart(Cart cart) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM CartItem ci WHERE ci.cart = :cart")
                    .setParameter("cart", cart)
                    .executeUpdate();
            cart.getItems().clear(); // Clear in-memory list
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

}