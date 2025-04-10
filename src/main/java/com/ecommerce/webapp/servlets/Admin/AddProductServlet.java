package com.ecommerce.webapp.servlets.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import com.ecommerce.webapp.entities.ProductCategory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value= "/AddProduct")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String productName = request.getParameter("product-name");
        String productDescription = request.getParameter("product-description");
        double productPrice = Double.parseDouble(request.getParameter("product-price"));
        String productImage = request.getParameter("product-image");
        ProductCategory productCategory = ProductCategory.valueOf(request.getParameter("product-category"));
        int stock_quantity = Integer.parseInt(request.getParameter("stock-quantity"));
        String productStatus = request.getParameter("product-status");

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

