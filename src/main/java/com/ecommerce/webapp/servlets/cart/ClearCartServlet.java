package com.ecommerce.webapp.servlets.cart;

import com.ecommerce.webapp.dao.CartDAO;
import com.ecommerce.webapp.entities.Cart;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebServlet("/clear-cart")
public class ClearCartServlet extends HttpServlet {

    private final CartDAO cartDAO = new CartDAO();
    private final CartService cartService = new CartService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = cartService.getOrCreateCart(session);

        try {
            if(cart.getUser() != null) { // Logged-in user
                cartDAO.clearCart(cart);
            } else { // Guest user
                cart.getItems().clear();
            }

            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getWriter(), Map.of(
                    "success", true,
                    "totalItems", 0,
                    "totalPrice", 0.0
            ));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            new ObjectMapper().writeValue(response.getWriter(), Map.of(
                    "error", "Failed to clear cart"
            ));
        }
    }
}