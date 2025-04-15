package com.ecommerce.webapp.servlets.Admin;

import com.ecommerce.webapp.dao.OrderDAO;
import com.ecommerce.webapp.entities.OrderStatus;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateOrderStatus")
public class UpdateOrderStatusServlet extends HttpServlet {
    
    private OrderDAO orderDAO = new OrderDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JsonObject jsonResponse = new JsonObject();
        
        try {
            String orderIdStr = request.getParameter("orderId");
            String statusStr = request.getParameter("status");
            
            if (orderIdStr == null || statusStr == null || orderIdStr.isEmpty() || statusStr.isEmpty()) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Order ID and status are required");
                out.print(jsonResponse.toString());
                return;
            }
            
            int orderId = Integer.parseInt(orderIdStr);
            OrderStatus newStatus = OrderStatus.valueOf(statusStr);
            
            boolean updated = orderDAO.updateStatus(orderId, newStatus);
            
            if (updated) {
                jsonResponse.addProperty("success", true);
                jsonResponse.addProperty("message", "Order status updated successfully");
            } else {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Failed to update order status");
            }
            
        } catch (NumberFormatException e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Invalid order ID format");
        } catch (IllegalArgumentException e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Invalid order status");
        } catch (Exception e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Error updating order status: " + e.getMessage());
        }
        
        out.print(jsonResponse.toString());
    }
}