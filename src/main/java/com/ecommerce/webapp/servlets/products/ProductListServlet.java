package com.ecommerce.webapp.servlets.products;

import com.ecommerce.webapp.entities.Product;
import com.ecommerce.webapp.entities.ProductCategory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/product-list")
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoryParam = request.getParameter("category");

        List<Product> allProducts = ProductFactory.getProducts();
        List<Product> filteredProducts;

        if (categoryParam != null && !categoryParam.isEmpty()) {
            try {
                ProductCategory category = ProductCategory.valueOf(categoryParam.toUpperCase());
                filteredProducts = ProductFactory.getProductsByCategory(category);
            } catch (IllegalArgumentException e) {
                filteredProducts = allProducts; // If invalid category, show all products
            }
        } else {
            filteredProducts = allProducts; // No category selected, show all
        }

        // Category counts
        int beansCategory = ProductFactory.getProductsByCategory(ProductCategory.BEANS).size();
        int mugsCategory = ProductFactory.getProductsByCategory(ProductCategory.MUGS).size();
        int machinesCategory = ProductFactory.getProductsByCategory(ProductCategory.MACHINES).size();

        // Set attributes
        request.setAttribute("products", filteredProducts);
        request.setAttribute("beansCategory", beansCategory);
        request.setAttribute("mugsCategory", mugsCategory);
        request.setAttribute("machinesCategory", machinesCategory);
        request.setAttribute("selectedCategory", categoryParam);

        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
        dispatcher.forward(request, response);
    }
}





