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

@WebServlet("/order-details")
public class OrderDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        // Check if user is logged in
        if (user == null) {
            // Redirect to login page if not logged in
            response.sendRedirect( "/home");
            return;
        }

        // Get orderId from request parameter
        String orderIdParam = request.getParameter("orderId");

        if (orderIdParam != null && !orderIdParam.isEmpty()) {
            try {
                int orderId = Integer.parseInt(orderIdParam);

                // Load order details with eager fetching of items and products
                OrderDAO orderDAO = new OrderDAO();
                Order order = orderDAO.findById(orderId);

                // Verify that the order belongs to the current user for security
                if (order != null && order.getUser().getUserId() == user.getUserId()) {
                    // Store order in request scope for JSTL access
                    request.setAttribute("order", order);
                } else {
                    // Handle unauthorized access to order
                    request.setAttribute("errorMessage", "You don't have permission to view this order");
                }
            } catch (NumberFormatException e) {
                // Handle invalid orderId parameter
                request.setAttribute("errorMessage", "Invalid order ID format");
            } catch (Exception e) {
                // Handle order not found or other errors
                request.setAttribute("errorMessage", "Order not found or error loading order details: " + e.getMessage());
            }
        } else {
            // Handle missing orderId parameter
            request.setAttribute("errorMessage", "No order ID provided");
        }

        // Forward to the JSP page
        request.getRequestDispatcher("/order-details.jsp").forward(request, response);
    }
}