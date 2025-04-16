package com.ecommerce.webapp.servlets.cart;

import com.ecommerce.webapp.dao.CartDAO;
import com.ecommerce.webapp.entities.Cart;
import com.ecommerce.webapp.entities.CartItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/remove-cart-item")
public class RemoveCartServlet extends HttpServlet {
    private final CartService cartService = new CartService();
    private final CartDAO cartDAO = new CartDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {




        try {
            // Existing code...
            int itemId = Integer.parseInt(request.getParameter("itemId"));

            HttpSession session = request.getSession();
            Cart cart = cartService.getOrCreateCart(session);

            if(cart.getUser() != null) { // Logged in user
                cartDAO.removeItemFromCart((long) itemId);
            } else { // Guest user
                cart.getItems().removeIf(item -> item.getId() == itemId);
            }

            // Return updated cart data
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getWriter(), getCartResponse(cart));

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid item ID");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error removing item from cart");
        }
    }

    private Map<String, Object> getCartResponse(Cart cart) {
        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", cart.getItems().size());
        response.put("totalPrice", cart.getTotalPrice());
        response.put("items", cart.getItems());
        return response;
    }
}