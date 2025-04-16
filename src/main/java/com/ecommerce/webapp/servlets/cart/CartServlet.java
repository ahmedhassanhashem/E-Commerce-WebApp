package com.ecommerce.webapp.servlets.cart;

import com.ecommerce.webapp.dao.CartDAO;
import com.ecommerce.webapp.entities.Cart;
import com.ecommerce.webapp.entities.Product;
import com.ecommerce.webapp.entities.User;
import com.ecommerce.webapp.servlets.products.ProductFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/cart/*")
public class CartServlet extends HttpServlet {
    private CartDAO cartDAO = new CartDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String path = request.getPathInfo();
        User user = (User) request.getSession().getAttribute("user");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");

        try {
            switch(path) {
                case "/add":
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    Product product = ProductFactory.getProductById(productId);
                    Cart cart = cartDAO.getCartByUser(user);
                    boolean added = cartDAO.addItemToCart(cart, product, quantity);
                    if(added) {
                        sendCartResponse(response, cart);
                    } else {
                        response.sendError(400, "Failed to add item");
                    }
                    break;

                case "/update":
                    int itemId = Integer.parseInt(request.getParameter("cartItemId"));
                    int newQty = Integer.parseInt(request.getParameter("quantity"));
                    boolean updated = cartDAO.updateItemQuantity(itemId, newQty);
                    if(updated) {
                        Cart updatedCart = cartDAO.getCartByUser(user);
                        sendCartResponse(response, updatedCart);
                    } else {
                        response.sendError(404, "Item not found");
                    }
                    break;

                case "/remove":
                    int removeId = Integer.parseInt(request.getParameter("cartItemId"));
                    boolean removed = cartDAO.removeItemFromCart(removeId);
                    if(removed) {
                        Cart updatedCart = cartDAO.getCartByUser(user);
                        sendCartResponse(response, updatedCart);
                    } else {
                        response.sendError(404, "Item not found");
                    }
                    break;

                case "/clear":
                    cartDAO.clearCart(user.getCart());
                    sendCartResponse(response, user.getCart());
                    break;
            }
        } catch (Exception e) {
            response.sendError(500, "Server error: " + e.getMessage());
        }
    }

    private void sendCartResponse(HttpServletResponse response, Cart cart)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("totalItems", cart.getItems().size());
        responseData.put("totalPrice", cart.getTotalPrice());
        mapper.writeValue(response.getWriter(), responseData);
    }
}


