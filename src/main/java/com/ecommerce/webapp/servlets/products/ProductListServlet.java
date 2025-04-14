package com.ecommerce.webapp.servlets.products;

import com.ecommerce.webapp.dao.ProductDAO;
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
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("search".equals(action)) {
            handleSearch(req, resp);
        } else if ("filter".equals(action)) {
            handleAjaxList(req, resp);
        } else {
            handleProductList(req, resp);
        }
    }

    private void handleProductList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Get category counts for sidebar
        req.setAttribute("beansCategory", productDAO.findByCategory(ProductCategory.BEANS).size());
        req.setAttribute("mugsCategory", productDAO.findByCategory(ProductCategory.MUGS).size());
        req.setAttribute("machinesCategory", productDAO.findByCategory(ProductCategory.MACHINES).size());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/product-list.jsp");
        dispatcher.forward(req, resp);
    }

    private void handleAjaxList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Parse all parameters
        ProductSearchResult result = ProductFactory.getFilteredProducts(
                parseCategory(req.getParameter("category")),
                parseDouble(req.getParameter("priceMin")),
                parseDouble(req.getParameter("priceMax")),
                req.getParameter("search"),
                "price", // Sort field
                !"highest".equals(req.getParameter("sort")), // ascending
                parseInt(req.getParameter("page"), 1),
                parseInt(req.getParameter("show"), 9)
        );

        req.setAttribute("products", result.getProducts());
        req.setAttribute("currentPage", parseInt(req.getParameter("page"), 1));
        req.setAttribute("totalPages", (int) Math.ceil((double) result.getTotalCount() /
                parseInt(req.getParameter("show"), 9)));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/product-list-fragment.jsp");
        dispatcher.forward(req, resp);
    }

    private void handleSearch(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String searchTerm = req.getParameter("search");
        ProductSearchResult result = ProductFactory.getFilteredProducts(
                null, null, null, searchTerm, "name", true, 1, 10
        );

        req.setAttribute("products", result.getProducts());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product-list.jsp");
        dispatcher.forward(req, resp);
    }

    // Helper methods for parameter parsing
    private ProductCategory parseCategory(String categoryParam) {
        try {
            return ProductCategory.valueOf(categoryParam.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

    private Double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return null;
        }
    }

    private int parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}