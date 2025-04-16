package com.ecommerce.webapp.servlets.cart;

import com.ecommerce.webapp.dao.CartDAO;
import com.ecommerce.webapp.entities.Cart;
import com.ecommerce.webapp.entities.CartItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/update-cart-item")
public class UpdateCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");

            if(cart == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Cart not found in session");
                return;
            }

            boolean success = false;
            if(cart.getUser() != null) {
                success = new CartDAO().updateItemQuantity(itemId, quantity);
            } else {
                cart.getItems().stream()
                        .filter(item -> item.getId() == itemId)
                        .findFirst()
                        .ifPresent(item -> {
                            item.setQuantity(quantity);
                        });
                success = true;
            }

            if(!success) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Failed to update cart item");
                return;
            }

            // Update session with the modified cart
            session.setAttribute("cart", cart);

            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getWriter(), getCartResponse(cart));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + e.getMessage());
        }
    }


    private Map<String, Object> getCartResponse(Cart cart) {
        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", cart.getItems().size());
        response.put("totalPrice", cart.getTotalPrice());

        // Create a simplified item list to avoid circular references
        List<Map<String, Object>> items = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("id", item.getId());
            itemMap.put("quantity", item.getQuantity());

            Map<String, Object> productMap = new HashMap<>();
            productMap.put("productId", item.getProduct().getProductId());
            productMap.put("name", item.getProduct().getName());
            productMap.put("price", item.getProduct().getPrice());
            productMap.put("image", item.getProduct().getImage());
            productMap.put("category", item.getProduct().getCategory().name());

            itemMap.put("product", productMap);
            items.add(itemMap);
        }
        response.put("items", items);

        return response;
    }
}