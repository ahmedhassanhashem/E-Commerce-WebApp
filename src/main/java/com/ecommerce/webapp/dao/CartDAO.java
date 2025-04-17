package com.ecommerce.webapp.dao;

import com.ecommerce.webapp.entities.Cart;
import com.ecommerce.webapp.entities.CartItem;
import com.ecommerce.webapp.entities.Product;
import com.ecommerce.webapp.entities.User;
import com.ecommerce.webapp.utils.PersistenceManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CartDAO {

    public Cart getCartByUser(User user) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            TypedQuery<Cart> query = em.createQuery(
                    "SELECT DISTINCT c FROM Cart c LEFT JOIN FETCH c.items i LEFT JOIN FETCH i.product WHERE c.user = :user",
                    Cart.class
            );
            query.setParameter("user", user);
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Create a new cart for the user if none exists
            Cart cart = new Cart();
            cart.setUser(user);
            em.persist(cart);
            return cart;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Add an item to cart (renamed from addToCart to match servlet calls)
     */
    public boolean addItemToCart(Cart cart, Product product, int quantity) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();

            // Make sure we have a managed cart
            if (!em.contains(cart)) {
                cart = em.merge(cart);
            }

            // Make sure we have a managed product
            if (!em.contains(product)) {
                product = em.find(Product.class, product.getProductId());
            }

            // Check if product already in cart
            CartItem existingItem = null;
            for (CartItem item : cart.getItems()) {
                if (item.getProduct().getProductId() == product.getProductId()) {
                    existingItem = item;
                    break;
                }
            }

            if (existingItem != null) {
                // Update quantity if product already in cart
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
            } else {
                // Add new item
                CartItem newItem = new CartItem();
                newItem.setCart(cart);
                newItem.setProduct(product);
                newItem.setQuantity(quantity);
                em.persist(newItem);
                cart.getItems().add(newItem);
            }

            // Make sure cart is updated in database
            em.merge(cart);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Legacy method for backward compatibility
     */
    public boolean addToCart(User user, Product product, int quantity) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();

            // Get or create cart
            Cart cart = getCartByUser(user);
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                em.persist(cart);
            }

            return addItemToCart(cart, product, quantity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update cart item quantity (renamed to match servlet calls)
     */
    public boolean updateItemQuantity(int cartItemId, int quantity) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            CartItem item = em.find(CartItem.class, cartItemId);
            if (item != null) {
                item.setQuantity(quantity);
                em.merge(item);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Legacy method for backward compatibility
     */
    public boolean updateCartItemQuantity(int cartItemId, int quantity) {
        return updateItemQuantity(cartItemId, quantity);
    }

    /**
     * Remove an item from cart (renamed to match servlet calls)
     */
    public boolean removeItemFromCart(int cartItemId) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();
            CartItem item = em.find(CartItem.class, cartItemId);
            if (item != null) {
                // Get the cart first
                Cart cart = item.getCart();

                // Remove from collection to maintain consistency
                cart.getItems().remove(item);

                // Delete the entity
                em.remove(item);

                // Update the cart
                em.merge(cart);

                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Legacy method for backward compatibility
     */
    public boolean removeCartItem(int cartItemId) {
        return removeItemFromCart(cartItemId);
    }

    public boolean clearCart(Cart cart) {
        try {
            EntityManager em = PersistenceManager.getEntityManager();

            // Find the managed cart instance
            Cart managedCart = em.merge(cart);
            if (managedCart == null) {
                return false;
            }

            // Get cart items to remove - using a separate query to avoid ConcurrentModificationException
            List<CartItem> items = em.createQuery(
                            "SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId",
                            CartItem.class
                    )
                    .setParameter("cartId", managedCart.getCartId())
                    .getResultList();

            // Remove each item individually
            for (CartItem item : items) {
                // First remove from the collection to maintain consistency
                managedCart.getItems().remove(item);
                // Then remove the entity
                em.remove(item);
            }

            // Make sure the collection is empty
            managedCart.getItems().clear();

            // Update the cart in session
            em.merge(managedCart);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}