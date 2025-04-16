package com.ecommerce.webapp.dao;

import com.ecommerce.webapp.entities.Cart;
import com.ecommerce.webapp.entities.CartItem;
import com.ecommerce.webapp.entities.Product;
import com.ecommerce.webapp.entities.User;
import com.ecommerce.webapp.utils.PersistenceManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartDAO {


    public Cart getCartByUser(User user) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT c FROM Cart c " +
                                    "LEFT JOIN FETCH c.items ci " +
                                    "LEFT JOIN FETCH ci.product " +
                                    "WHERE c.user = :user", Cart.class)
                    .setParameter("user", user)
                    .getSingleResult();
        } catch (NoResultException e) {
            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
            em.persist(cart);
            return cart;
        }
    }

    public CartItem getCartItemById(int cartItemId) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT ci FROM CartItem ci JOIN FETCH ci.product WHERE ci.id = :id",
                            CartItem.class)
                    .setParameter("id", cartItemId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean updateItemQuantity(int cartItemId, int newQuantity) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            CartItem item = em.find(CartItem.class, cartItemId);
            if(item != null) {
                if(newQuantity <= 0) {
                    em.remove(item);
                } else {
                    item.setQuantity(newQuantity);
                    em.merge(item);
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Find cart items by user for display in session.
     * This version detaches the cart items from the persistence context to avoid circular references,
     * but maintains the essential cart ID for integrity.
     */
    public List<CartItem> findCartItemsByUserForSession(User user) {
        EntityManager em = PersistenceManager.getEntityManager();

        // First, get all cart item IDs for the user's cart using the user reference
        List<Integer> cartItemIds = em.createQuery(
                        "SELECT ci.id FROM CartItem ci WHERE ci.cart.user.userId = :userId ORDER BY ci.id DESC", Integer.class)
                .setParameter("userId", user.getUserId())
                .getResultList();

        // Then load each cart item individually with its product information
        List<CartItem> cartItems = new ArrayList<>();
        for (Integer cartItemId : cartItemIds) {
            try {
                // Use a query that eagerly fetches the associated product
                CartItem item = em.createQuery(
                                "SELECT DISTINCT ci FROM CartItem ci " +
                                        "LEFT JOIN FETCH ci.product " +
                                        "WHERE ci.id = :cartItemId", CartItem.class)
                        .setParameter("cartItemId", cartItemId)
                        .getSingleResult();

                // IMPORTANT: Instead of keeping the full Cart reference, create a lightweight Cart
                // that maintains just the cart ID to break the bi-directional link.
                Cart lightCart = new Cart();
                lightCart.setCartId(item.getCart().getCartId());
                item.setCart(lightCart);

                // Detach the cart item from the persistence context to prevent accidental updates
                em.detach(item);

                cartItems.add(item);
            } catch (Exception e) {
                // Log error or skip this cart item
                System.err.println("Error loading cart item #" + cartItemId + ": " + e.getMessage());
            }
        }

        return cartItems;
    }


//    public boolean addItemToCart(Cart cart, Product product, int quantity) {
//        try {
//            EntityManager em = PersistenceManager.getEntityManager();
//
//            // Check if item already exists in cart
//            Optional<CartItem> existingItem = cart.getItems().stream()
//                    .filter(item -> item.getProduct().getProductId() == product.getProductId())
//                    .findFirst();
//
//            if(existingItem.isPresent()) {
//                CartItem item = existingItem.get();
//                item.setQuantity(item.getQuantity() + quantity);
//                em.merge(item);
//            } else {
//                CartItem newItem = new CartItem();
//                newItem.setCart(cart);
//                newItem.setProduct(product);
//                newItem.setQuantity(quantity);
//                em.persist(newItem);
//            }
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }



//    public boolean addItemToCart(Cart cart, Product product, int quantity) {
//        EntityManager em = PersistenceManager.getEntityManager();
//        try {
//            // Basic validation
//            if (quantity <= 0 || quantity > product.getStock()) {
//                return false;
//            }
//
//            // Check if the product is already in the cart
//            CartItem existingItem = null;
//            for (CartItem item : cart.getItems()) {
//                if (item.getProduct().getProductId() == product.getProductId()) {
//                    existingItem = item;
//                    break;
//                }
//            }
//
//            if (existingItem != null) {
//                // Update existing item
//                int newQty = existingItem.getQuantity() + quantity;
//                if (newQty > product.getStock()) return false;
//                existingItem.setQuantity(newQty);
//                em.merge(existingItem);
//            } else {
//                // Create new item
//                CartItem newItem = new CartItem();
//                newItem.setCart(cart);
//                newItem.setProduct(product);
//                newItem.setQuantity(quantity);
//                em.persist(newItem);
//
//                // Important: Update both sides of bidirectional relationship
//                cart.getItems().add(newItem);
//            }
//
//            em.flush(); // Force database write
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace(); // Better error logging
//            return false;
//        }
//    }

//    public boolean addItemToCart(Cart cart, Product product, int quantity) {
//        EntityManager em = PersistenceManager.getEntityManager();
//        try {
//            // Check stock
//            if(quantity > product.getStock()) {
//                return false;
//            }
//
//            // Check existing items
//            CartItem existingItem = cart.getItems().stream()
//                    .filter(item -> item.getProduct().getProductId() == product.getProductId())
//                    .findFirst()
//                    .orElse(null);
//
//            if(existingItem != null) {
//                existingItem.setQuantity(existingItem.getQuantity() + quantity);
//                em.merge(existingItem);
//            } else {
//                CartItem newItem = new CartItem();
//                newItem.setCart(cart);
//                newItem.setProduct(product);
//                newItem.setQuantity(quantity);
//                em.persist(newItem);
//                cart.getItems().add(newItem);
//            }
//
//            em.flush();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public boolean addItemToCart(Cart cart, Product product, int quantity) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            if (quantity > product.getStock()) {
                return false;
            }

            CartItem existingItem = cart.getItems().stream()
                    .filter(item -> item.getProduct().getProductId() == product.getProductId())
                    .findFirst().orElse(null);

            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                em.merge(existingItem);
            } else {
                CartItem newItem = new CartItem();
                newItem.setCart(cart);
                newItem.setProduct(product);
                newItem.setQuantity(quantity);
                em.persist(newItem);
                cart.getItems().add(newItem);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



//    public boolean updateItemQuantity(int cartItemId, int newQuantity) {
//        try {
//            EntityManager em = PersistenceManager.getEntityManager();
//            CartItem item = em.find(CartItem.class, cartItemId);
//            if(item != null) {
//                item.setQuantity(newQuantity);
//                em.merge(item);
//                return true;
//            }
//            return false;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public boolean removeItemFromCart(int cartItemId) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            CartItem item = em.find(CartItem.class, cartItemId);
            if(item != null) {
                em.remove(item);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clearCart(Cart cart) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            TypedQuery<CartItem> query = em.createQuery(
                    "DELETE FROM CartItem ci WHERE ci.cart = :cart", CartItem.class);
            query.setParameter("cart", cart);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}