package com.ecommerce.webapp.servlets.Admin;

import com.ecommerce.webapp.dao.OrderDAO;
import com.ecommerce.webapp.entities.Order;
import com.ecommerce.webapp.entities.OrderStatus;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/getOrders")
public class GetOrdersServlet extends HttpServlet {
    
    private OrderDAO orderDAO = new OrderDAO();
    private Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        try {

            String searchTerm = request.getParameter("search");
            String statusFilter = request.getParameter("status");
            
            List<Order> allOrders = orderDAO.findAll();
            List<Order> filteredOrders = new ArrayList<>(allOrders);
            
            if (statusFilter != null && !statusFilter.equals("all")) {
                OrderStatus status = OrderStatus.valueOf(statusFilter.toUpperCase());
                filteredOrders = filteredOrders.stream()
                                              .filter(order -> order.getStatus() == status)
                                              .collect(Collectors.toList());
            }
            
            // Apply search filter (by order ID or customer email)
            if (searchTerm != null && !searchTerm.isEmpty()) {
                filteredOrders = filteredOrders.stream()
                                             .filter(order -> 
                                                 String.valueOf(order.getOrderId()).contains(searchTerm) || 
                                                 (order.getUser() != null && 
                                                  order.getUser().getEmail() != null && 
                                                  order.getUser().getEmail().contains(searchTerm)))
                                             .collect(Collectors.toList());
            }
            
            List<JsonObject> ordersJson = new ArrayList<>();
            for (Order order : filteredOrders) {
                JsonObject orderJson = new JsonObject();
                orderJson.addProperty("orderId", order.getOrderId());
                orderJson.addProperty("customerId", order.getUser().getUserId());
                orderJson.addProperty("customerEmail", order.getUser().getEmail());
                orderJson.addProperty("itemCount", order.getItems().size());
                orderJson.addProperty("totalPrice", order.getTotalPrice());
                orderJson.addProperty("status", order.getStatus().toString());
                ordersJson.add(orderJson);
            }
            
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("success", true);
            jsonResponse.add("orders", gson.toJsonTree(ordersJson));
            
            out.print(jsonResponse.toString());
            
        } catch (Exception e) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("success", false);
            errorResponse.addProperty("message", "Error loading orders: " + e.getMessage());
            out.print(errorResponse.toString());
        }
    }
}