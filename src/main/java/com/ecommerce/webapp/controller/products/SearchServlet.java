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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("main-search");
        if (keyword == null || keyword.trim().isEmpty()) {
            response.sendRedirect("empty-search.jsp");
            return;
        }

        List<Product> allProducts = ProductFactory.getProducts();
        List<Product> searchResults = allProducts.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        // Calculate category counts FROM SEARCH RESULTS (not all products)
        int beansCategory = (int) searchResults.stream()
                .filter(p -> p.getCategory() == ProductCategory.BEANS).count();
        int mugsCategory = (int) searchResults.stream()
                .filter(p -> p.getCategory() == ProductCategory.MUGS).count();
        int machinesCategory = (int) searchResults.stream()
                .filter(p -> p.getCategory() == ProductCategory.MACHINES).count();

        request.setAttribute("searchKeyword", keyword);
        request.setAttribute("products", searchResults);
        request.setAttribute("beansCategory", beansCategory);
        request.setAttribute("mugsCategory", mugsCategory);
        request.setAttribute("machinesCategory", machinesCategory);

        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
        dispatcher.forward(request, response);
    }
}
