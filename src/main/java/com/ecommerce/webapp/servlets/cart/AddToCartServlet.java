package com.ecommerce.webapp.servlets.cart;

import com.ecommerce.webapp.dao.CartDAO;
import com.ecommerce.webapp.dao.ProductDAO;
import com.ecommerce.webapp.entities.Cart;
import com.ecommerce.webapp.entities.CartItem;
import com.ecommerce.webapp.entities.Product;
import com.ecommerce.webapp.servlets.products.ProductFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private final CartService cartService = new CartService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        Cart cart = cartService.getOrCreateCart(session);
        Product product = ProductFactory.getProductById(productId);

        if(cart.getUser() != null) { // Logged in user
            new CartDAO().addItemToCart(cart, product, quantity);
        } else { // Guest user
            Optional<CartItem> existingItem = cart.getItems().stream()
                    .filter(item -> item.getProduct().getProductId() == productId)
                    .findFirst();

            if(existingItem.isPresent()) {
                existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
            } else {
                CartItem newItem = new CartItem();
                newItem.setProduct(product);
                newItem.setQuantity(quantity);
                cart.getItems().add(newItem);
            }
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