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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                cartDAO.removeItemFromCart( itemId);
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