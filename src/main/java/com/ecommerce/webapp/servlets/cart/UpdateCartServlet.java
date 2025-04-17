package com.ecommerce.webapp.servlets.cart;

import java.io.IOException;

import com.ecommerce.webapp.dao.CartDAO;
import com.ecommerce.webapp.dto.CartDTO;
import com.ecommerce.webapp.dto.Mapper;
import com.ecommerce.webapp.entities.Cart;
import com.ecommerce.webapp.entities.User;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update-cart-item")
public class UpdateCartServlet extends HttpServlet {
    private CartDAO cartDAO = new CartDAO();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in");
            return;
        }

        int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        try {
            // Attempt to update the quantity, but don’t stop if it fails
            cartDAO.updateItemQuantity(cartItemId, quantity);

            // Always fetch and return the current cart state
            Cart cart = cartDAO.getCartByUser(user);
            session.setAttribute("cart", cart);
            CartDTO cartDTO = Mapper.mapToDTO(cart);

            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(cartDTO));
        } catch (Exception e) {
            // Log the exception for debugging, but still return the cart
            log("Error updating cart item: " + e.getMessage(), e);
            Cart cart = cartDAO.getCartByUser(user);
            session.setAttribute("cart", cart);
            CartDTO cartDTO = Mapper.mapToDTO(cart);
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(cartDTO));
        }
    }
}