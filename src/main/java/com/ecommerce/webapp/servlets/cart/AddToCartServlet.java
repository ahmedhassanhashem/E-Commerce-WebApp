package com.ecommerce.webapp.servlets.cart;

import com.ecommerce.webapp.dao.CartDAO;
import com.ecommerce.webapp.dao.ProductDAO;
import com.ecommerce.webapp.entities.Cart;
import com.ecommerce.webapp.entities.CartItem;
import com.ecommerce.webapp.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private final CartService cartService = new CartService();
    private final ProductDAO productDAO = new ProductDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            HttpSession session = request.getSession();
            Cart cart = cartService.getOrCreateCart(session);
            Product product = productDAO.findById(productId);

            if(product == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Product not found");
                return;
            }

            boolean success = false;
            if(cart.getUser() != null) { // Logged in user
                success = new CartDAO().addItemToCart(cart, product, quantity);
            }
//            else { // Guest user
//                Optional<CartItem> existingItem = cart.getItems().stream()
//                        .filter(item -> item.getProduct().getProductId() == productId)
//                        .findFirst();
//
//                if(existingItem.isPresent()) {
//                    existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
//                    success = true;
//                } else {
//                    CartItem newItem = new CartItem();
//                    newItem.setId(cartService.generateGuestItemId());
//                    newItem.setProduct(product);
//                    newItem.setQuantity(quantity);
//                    newItem.setCart(cart);
//                    cart.getItems().add(newItem);
//                    success = true;
//                }
//            }

            if(!success) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Failed to add item to cart");
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


