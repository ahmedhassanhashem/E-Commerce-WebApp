package com.ecommerce.webapp.servlets.Admin;

import com.ecommerce.webapp.dao.ProductDAO;
import com.ecommerce.webapp.entities.Product;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    
    private ProductDAO productDAO = new ProductDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JsonObject jsonResponse = new JsonObject();
        
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            
            Product product = productDAO.findById(productId);

            product.setStock(0);
            product.setPrice(0.0);  
            product.setStatus();
            
            boolean deleted = productDAO.updateProduct(product);
            
            if (deleted) {
                jsonResponse.addProperty("success", true);
                jsonResponse.addProperty("message", "Product deleted successfully");
            } else {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Failed to delete product");
            }
            
        } catch (NumberFormatException e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Invalid product ID");
            e.printStackTrace();
        } catch (Exception e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Error deleting product: " + e.getMessage());
            e.printStackTrace();
        }
        
        out.print(jsonResponse.toString());
    }
}