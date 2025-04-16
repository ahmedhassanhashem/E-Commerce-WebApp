package com.ecommerce.webapp.servlets.cart;

import com.ecommerce.webapp.dao.CartDAO;
import com.ecommerce.webapp.entities.*;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

public class CartService {
    private final CartDAO cartDAO = new CartDAO();

    public Cart getOrCreateCart(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        if(user != null) {
            if(cart == null) {
                cart = cartDAO.getCartByUser(user);
                session.setAttribute("cart", cart);
            }
            return cart;
        }

        // For guest users
        if(cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    public void syncCartWithDatabase(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Cart sessionCart = (Cart) session.getAttribute("cart");

        if(user != null && sessionCart != null) {
            Cart dbCart = cartDAO.getCartByUser(user);
            // Merge session cart with database cart
            sessionCart.getItems().forEach(item -> {
                cartDAO.addItemToCart(dbCart, item.getProduct(), item.getQuantity());
            });
            session.setAttribute("cart", dbCart);
        }
    }
}