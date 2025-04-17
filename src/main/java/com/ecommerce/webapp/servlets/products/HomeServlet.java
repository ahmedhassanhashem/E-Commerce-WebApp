package com.ecommerce.webapp.servlets.products;

import com.ecommerce.webapp.entities.Product;
import com.ecommerce.webapp.entities.ProductCategory;
import com.ecommerce.webapp.entities.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // Get products
        List<Product> beans = ProductFactory.getProductsByCategory(ProductCategory.BEANS);
        List<Product> mugs = ProductFactory.getProductsByCategory(ProductCategory.MUGS);
        List<Product> machines = ProductFactory.getProductsByCategory(ProductCategory.MACHINES);

        request.setAttribute("beans", beans);
        request.setAttribute("mugs", mugs);
        request.setAttribute("machines", machines);

        // If it's a refresh request, use the fragment template instead of full page
        if ("refresh".equals(action)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
        }
    }
}