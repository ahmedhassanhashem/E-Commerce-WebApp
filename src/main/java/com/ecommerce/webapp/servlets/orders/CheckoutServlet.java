package com.ecommerce.webapp.servlets.orders;

import com.ecommerce.webapp.dao.*;
import com.ecommerce.webapp.entities.*;
import com.ecommerce.webapp.utils.PersistenceManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        CartDAO cartDAO = new CartDAO();
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();
        UserDAO userDAO = new UserDAO();

        try {
            // Ensure we're working with a fresh managed entity
            user = userDAO.findByEmail(user.getEmail());
            if (user == null) {
                request.setAttribute("error", "User session invalid. Please login again.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            Cart cart = cartDAO.getCartByUser(user);
            if (cart == null || cart.getItems().isEmpty()) {
                request.setAttribute("error", "Your cart is empty");
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
                return;
            }

            List<CartItem> cartItems = cart.getItems();
            double total = cart.getTotalPrice();

            // Check product stock
            for (CartItem item : cartItems) {
                Product product = item.getProduct();
                // Get fresh product data to ensure current stock level
                Product freshProduct = productDAO.findById(product.getProductId());
                if (item.getQuantity() > freshProduct.getStock()) {
                    request.setAttribute("error", "Insufficient stock for " + freshProduct.getName());
                    request.getRequestDispatcher("/checkout.jsp").forward(request, response);
                    return;
                }
            }

            // Check user's credit balance
            if (user.getCreditBalance() < total) {
                request.setAttribute("error", "Insufficient credit balance");
                request.getRequestDispatcher("/checkout.jsp").forward(request, response);
                return;
            }

            // Create order first
            Order order = new Order();
            order.setUser(user);
            order.setTotalPrice(total);
            order.setStatus(OrderStatus.ACCEPTED);
            order.setItems(new ArrayList<>());

            // Add items to order
            for (CartItem cartItem : cartItems) {
                Product product = productDAO.findById(cartItem.getProduct().getProductId());

                // Update product stock
                product.setStock(product.getStock() - cartItem.getQuantity());
                productDAO.updateProduct(product);

                // Create order item
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setItemPrice(product.getPrice());

                order.getItems().add(orderItem);
            }

            // Save the order
            boolean orderSaved = orderDAO.addOrder(order);
            if (!orderSaved) {
                throw new ServletException("Failed to save order");
            }

            // Update user balance
            user.setCreditBalance(user.getCreditBalance() - total);
            userDAO.updateUser(user);

            // Update session user
            session.setAttribute("user", user);

            // Clear the cart after successful order creation
            boolean cartCleared = cartDAO.clearCart(cart);
            if (!cartCleared) {
                // Log the error but continue - order is already saved
                System.err.println("Warning: Failed to clear cart after checkout");
            }

            // Update cart in session
            session.setAttribute("cart", cartDAO.getCartByUser(user));

            // Redirect to orders page
            response.sendRedirect("my-orders.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Checkout failed: " + e.getMessage());
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
        }
    }
}