package com.ecommerce.webapp.controller.products;

import com.ecommerce.webapp.model.Product;
import com.ecommerce.webapp.model.ProductCategory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/product-list")
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoryParam = request.getParameter("category"); // Get category from URL

        List<Product> allProducts = ProductFactory.getProducts();
        List<Product> filteredProducts;

        if (categoryParam != null && !categoryParam.isEmpty()) {
            try {
                filteredProducts = allProducts.stream()
                        .filter(product -> product.getCategory().name().equalsIgnoreCase(categoryParam))
                        .toList();
            } catch (IllegalArgumentException e) {
                filteredProducts = allProducts; // If invalid category, show all products
            }
        } else {
            filteredProducts = allProducts; // No category selected, show all
        }

        // Category counts
        int beansCategory = (int) allProducts.stream().filter(p -> p.getCategory() == ProductCategory.BEANS).count();
        int mugsCategory = (int) allProducts.stream().filter(p -> p.getCategory() == ProductCategory.MUGS).count();
        int machinesCategory = (int) allProducts.stream().filter(p -> p.getCategory() == ProductCategory.MACHINES).count();

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





