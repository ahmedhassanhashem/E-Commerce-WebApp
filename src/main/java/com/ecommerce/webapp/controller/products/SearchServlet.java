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
import java.util.Locale;
import java.util.stream.Collectors;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve search keyword from the request
        String keyword = request.getParameter("main-search");
        if (keyword == null || keyword.trim().isEmpty()) {
            // Optionally, redirect to a "no results" or home page if no keyword was provided.
            response.sendRedirect("empty-search.jsp");
            return;
        }

        // Fetch all products (replace this with your actual data access code)
        List<Product> allProducts = ProductFactory.getProducts();

        // Filter products by name (case-insensitive search using contains)
        List<Product> searchResults = allProducts.stream()
                .filter(p -> p.getName().toLowerCase(Locale.ENGLISH).contains(keyword.toLowerCase(Locale.ENGLISH)))
                .collect(Collectors.toList());

        // Optionally, set also a message or the search term itself for display in the JSP
        request.setAttribute("searchKeyword", keyword);
        request.setAttribute("products", searchResults);

        // Forward the request to the product list page (or a dedicated search results page)
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
        dispatcher.forward(request, response);
    }

    // Optionally implement doPost if your form uses POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // For simplicity, delegate to doGet
        doGet(request, response);
    }
}
