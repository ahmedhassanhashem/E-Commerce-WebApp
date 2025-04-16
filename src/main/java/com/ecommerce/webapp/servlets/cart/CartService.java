package com.ecommerce.webapp.servlets.cart;

import com.ecommerce.webapp.dao.CartDAO;
import com.ecommerce.webapp.entities.*;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CartService {
    private final CartDAO cartDAO = new CartDAO();
    // Counter for guest cart items
//    private static final AtomicInteger GUEST_ITEM_ID_COUNTER = new AtomicInteger(1000000);

    public Cart getOrCreateCart(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        if(user != null) {
            // For logged-in users, retrieve from database
            if(cart == null || cart.getUser() == null) {
                cart = cartDAO.getCartByUser(user);
                session.setAttribute("cart", cart);
            }
        } else {
            // For guest users
            if(cart == null) {
                cart = new Cart();
                cart.setCartId(-1); // Temporary ID for guest cart
                session.setAttribute("cart", cart);
            }
        }
        return cart;
    }

    public void mergeGuestCartWithUserCart(HttpSession session, User user) {
        Cart guestCart = (Cart) session.getAttribute("cart");
        if(guestCart != null && guestCart.getItems().size() > 0) {
            Cart userCart = cartDAO.getCartByUser(user);

            // Transfer guest cart items to user cart
            for(CartItem guestItem : guestCart.getItems()) {
                cartDAO.addItemToCart(userCart, guestItem.getProduct(), guestItem.getQuantity());
            }

            // Replace the session cart with the user's cart
            session.setAttribute("cart", userCart);
        }
    }

//    public int generateGuestItemId() {
//        return GUEST_ITEM_ID_COUNTER.incrementAndGet();
//    }
}