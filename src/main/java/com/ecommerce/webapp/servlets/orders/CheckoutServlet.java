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
        ProductDAO productDAO = new ProductDAO(); // Assume this exists

        Cart cart = cartDAO.getCartByUser(user);
        List<CartItem> cartItems = cart.getItems();

        // Validate stock and calculate total
        try {
            double total = cart.getTotalPrice();

            // Check product stock
            for (CartItem item : cartItems) {
                Product product = item.getProduct();
                if (item.getQuantity() > product.getStock()) {
                    request.setAttribute("error", "Insufficient stock for " + product.getName());
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

            // Deduct stock and update user balance
            for (CartItem item : cartItems) {
                Product product = item.getProduct();
                product.setStock(product.getStock() - item.getQuantity());
                productDAO.updateProduct(product); // Implement this in ProductDAO
            }

            user.setCreditBalance(user.getCreditBalance() - total);
            new UserDAO().updateUser(user);

            // Create and save order
            Order order = new Order();
            order.setUser(user);
            order.setTotalPrice(total);
            order.setStatus(OrderStatus.ACCEPTED);

            List<OrderItem> orderItems = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setItemPrice(cartItem.getProduct().getPrice());
                orderItems.add(orderItem);
            }
            order.setItems(orderItems);

            orderDAO.addOrder(order);

            // Clear the cart
            cartDAO.clearCart(cart);

            response.sendRedirect("my-orders.jsp");

        } catch (Exception e) {
            throw new ServletException("Checkout failed", e);
        }
    }
}