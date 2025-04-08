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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/product-list-ajax")
public class ProductListAjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve parameters with default values
        String pageParam = request.getParameter("page");
        String showParam = request.getParameter("show");
        String sortParam = request.getParameter("sort");
        String categoryParam = request.getParameter("category");
        String priceMinParam = request.getParameter("priceMin");
        String priceMaxParam = request.getParameter("priceMax");
        String search = request.getParameter("search");



        int page = 1;
        int itemsPerPage = 9; // Default to 9
        String sort = ""; // Default to no sorting (empty string)


        // Safely parse parameters
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                // Keep default value
            }
        }

        if (showParam != null && !showParam.isEmpty()) {
            try {
                itemsPerPage = Integer.parseInt(showParam);
            } catch (NumberFormatException e) {
                // Keep default value
            }
        }

        if (sortParam != null && !sortParam.isEmpty()) {
            sort = sortParam;
        }

        // Fetch product list from your factory (or service)
        List<Product> products = ProductFactory.getProducts();

        if (search != null && !search.isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getName().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (categoryParam != null && !categoryParam.isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getCategory().toString().equalsIgnoreCase(categoryParam))
                    .collect(Collectors.toList());
        }
        if (priceMinParam != null && !priceMinParam.isEmpty()) {
            try {
                double priceMin = Double.parseDouble(priceMinParam);
                products = products.stream()
                        .filter(p -> p.getPrice() >= priceMin)
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) { }
        }
        if (priceMaxParam != null && !priceMaxParam.isEmpty()) {
            try {
                double priceMax = Double.parseDouble(priceMaxParam);
                products = products.stream()
                        .filter(p -> p.getPrice() <= priceMax)
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) { }
        }


        // Sort products by price only if sort is specified
        if ("lowest".equals(sort)) {
            products.sort(Comparator.comparing(Product::getPrice));
        } else if ("highest".equals(sort)) {
            products.sort(Comparator.comparing(Product::getPrice).reversed());
        }
        // If sort is empty string (default), no sorting is applied

        // Pagination logic: determine the subset of products to display
        int totalProducts = products.size();
        int totalPages = (int) Math.ceil((double) totalProducts / itemsPerPage);

        // Ensure we have at least 1 page
        if (totalPages == 0) {
            totalPages = 1;
        }

        // Ensure page is within valid range
        if (page > totalPages) {
            page = totalPages;
        }

        int startIndex = (page - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalProducts);

        // Check to prevent index out of bounds
        if (startIndex >= totalProducts) {
            startIndex = 0;
            endIndex = Math.min(itemsPerPage, totalProducts);
            page = 1;
        }

        List<Product> pagedProducts = products.subList(startIndex, endIndex);

        int beansCategory = (int) products.stream()
                .filter(p -> p.getCategory() == ProductCategory.BEANS).count();
        int mugsCategory = (int) products.stream()
                .filter(p -> p.getCategory() == ProductCategory.MUGS).count();
        int machinesCategory = (int) products.stream()
                .filter(p -> p.getCategory() == ProductCategory.MACHINES).count();

        request.setAttribute("beansCategory", beansCategory);
        request.setAttribute("mugsCategory", mugsCategory);
        request.setAttribute("machinesCategory", machinesCategory);
        request.setAttribute("selectedCategory", categoryParam);
        // Set attributes for the fragment
        request.setAttribute("products", pagedProducts);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("itemsPerPage", itemsPerPage);
        request.setAttribute("sort", sort);
        request.setAttribute("searchKeyword", search);


        // Forward to the JSP fragment
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list-fragment.jsp");
        dispatcher.forward(request, response);
    }
}