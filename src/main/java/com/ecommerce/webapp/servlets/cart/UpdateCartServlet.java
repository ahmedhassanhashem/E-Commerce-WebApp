package com.ecommerce.webapp.servlets.cart;

import com.ecommerce.webapp.dao.CartDAO;
import com.ecommerce.webapp.entities.Cart;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/update-cart-item")
public class UpdateCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if(cart.getUser() != null) {
            new CartDAO().updateItemQuantity( itemId, quantity);
        } else {
            cart.getItems().stream()
                    .filter(item -> item.getId() == itemId)
                    .findFirst()
                    .ifPresent(item -> item.setQuantity(quantity));
        }

        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getWriter(), getCartResponse(cart));
    }


    private Map<String, Object> getCartResponse(Cart cart) {
        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", cart.getItems().size());
        response.put("totalPrice", cart.getTotalPrice());
        response.put("items", cart.getItems());
        return response;
    }
}