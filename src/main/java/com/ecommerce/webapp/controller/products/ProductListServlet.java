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

@WebServlet("/product-list")
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products = ProductFactory.getProducts();

        int beansCategory = (int) products.stream().filter(item -> item.getCategory() == ProductCategory.BEANS).count();
        int mugsCategory = (int) products.stream().filter(item -> item.getCategory() == ProductCategory.MUGS).count();
        int machinesCategory = (int) products.stream().filter(item -> item.getCategory() == ProductCategory.MACHINES).count();



        ///////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////

        request.setAttribute("products", products);
        request.setAttribute("beansCategory", beansCategory);
        request.setAttribute("mugsCategory", mugsCategory);
        request.setAttribute("machinesCategory", machinesCategory);


        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
        dispatcher.forward(request, response);

    }



}
