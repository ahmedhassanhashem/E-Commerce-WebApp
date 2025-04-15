package com.ecommerce.webapp.servlets.Admin;

import com.ecommerce.webapp.dao.OrderDAO;
import com.ecommerce.webapp.dao.ProductDAO;
import com.ecommerce.webapp.dao.UserDAO;
import com.ecommerce.webapp.entities.OrderStatus;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dashboard-data")
public class AdminDashboardServlet extends HttpServlet {
    private ProductDAO productDAO;
    private UserDAO userDAO;
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
        userDAO = new UserDAO();
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
            
            
            long productsInStock = productDAO.getProductInStockCount();
            
            long productsOutOfStock = productDAO.getProductOutOfStockCount();
                   
            long userCount = userDAO.getUsersCount();
            
            long processingOrders = orderDAO.countByStatus(OrderStatus.PENDING);
            
            long completedOrders = orderDAO.countByStatus(OrderStatus.ACCEPTED);
            
            long cancelledOrders = orderDAO.countByStatus(OrderStatus.CANCELLED);

            // Create JSON response
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("productsInStock", productsInStock);
            jsonResponse.addProperty("productsOutOfStock", productsOutOfStock);
            jsonResponse.addProperty("userCount", userCount);
            jsonResponse.addProperty("processingOrders", processingOrders);
            jsonResponse.addProperty("completedOrders", completedOrders);
            jsonResponse.addProperty("cancelledOrders", cancelledOrders);

            // Log for debugging
            System.out.println("Dashboard data: " + jsonResponse.toString());
            
            response.getWriter().write(jsonResponse.toString());
        
    }
}