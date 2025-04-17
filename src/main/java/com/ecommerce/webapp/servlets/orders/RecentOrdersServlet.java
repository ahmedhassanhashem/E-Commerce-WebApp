package com.ecommerce.webapp.servlets.orders;

import com.ecommerce.webapp.dao.OrderDAO;
import com.ecommerce.webapp.entities.Order;
import com.ecommerce.webapp.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/my-account")
public class RecentOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {

            response.sendRedirect(request.getContextPath() + "/home");

            return;
        }

        User user = (User) session.getAttribute("user");
        OrderDAO orderDAO = new OrderDAO();

        try {
            // Fetch orders with items and products (eagerly loaded)
            // Use the safe version that prevents circular references
            List<Order> orders = orderDAO.findByUserForSession(user);

            // Debug information
            System.out.println("Found " + orders.size() + " orders for user " + user.getName());
            for (Order order : orders) {
                System.out.println("Order #" + order.getOrderId() + " has " +
                        (order.getItems() != null ? order.getItems().size() : 0) + " items");
            }

            // Update user object with the orders
            user.setOrders(orders);

            // Update the user in the session
            session.setAttribute("user", user);

        } catch (Exception e) {
            // Log the error but don't let it crash the page
            System.err.println("Error loading orders: " + e.getMessage());
            e.printStackTrace();
        }

        // Forward to JSP
        request.getRequestDispatcher("/my-account.jsp").forward(request, response);
    }
}
