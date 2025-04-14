package com.ecommerce.webapp.servlets.Admin;

import com.ecommerce.webapp.dao.ProductDAO;
import com.ecommerce.webapp.entities.Product;
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

@WebServlet("/getProducts")
public class GetProductsServlet extends HttpServlet {
    
    private ProductDAO productDAO = new ProductDAO();
    private Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        try {
            // Get search and filter parameters
            String searchTerm = request.getParameter("search");
            String categoryFilter = request.getParameter("category");
            
            // Get all products
            List<Product> allProducts = productDAO.getAllProducts();
            List<Product> filteredProducts = new ArrayList<>(allProducts);
            
            // Apply filters if provided
            if (categoryFilter != null && !categoryFilter.isEmpty()) {
                filteredProducts = filteredProducts.stream()
                    .filter(product -> product.getCategory().toString().equals(categoryFilter))
                    .toList();
            }
            
            // Apply search if provided
            if (searchTerm != null && !searchTerm.isEmpty()) {
                // Check if search term is numeric (for product ID)
                if (searchTerm.matches("\\d+")) {
                    try {
                        int productId = Integer.parseInt(searchTerm);
                        filteredProducts = filteredProducts.stream()
                            .filter(product -> product.getProductId() == productId)
                            .toList();
                    } catch (NumberFormatException e) {
                        // If parsing fails, search by name
                        filteredProducts = productDAO.searchByName(searchTerm);
                    }
                } else {
                    // Search by name
                    filteredProducts = productDAO.searchByName(searchTerm);
                }
            }
            
            // Create JSON response
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("success", true);
            jsonResponse.add("products", gson.toJsonTree(filteredProducts));
            
            out.print(jsonResponse.toString());
            
        } catch (Exception e) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("success", false);
            errorResponse.addProperty("message", "Error loading products: " + e.getMessage());
            out.print(errorResponse.toString());
        }
    }
}