package com.ecommerce.webapp.controller.products;

import com.ecommerce.webapp.model.Product;
import com.ecommerce.webapp.model.ProductCategory;
import com.ecommerce.webapp.model.ProductColor;
import com.ecommerce.webapp.model.ProductSize;
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

        int beansCategory = products.stream().filter(item -> item.getCategory() == ProductCategory.BEANS).toArray().length;
        int mugsCategory = products.stream().filter(item -> item.getCategory() == ProductCategory.MUGS).toArray().length;
        int machinesCategory = products.stream().filter(item -> item.getCategory() == ProductCategory.MACHINES).toArray().length;

        int light = products.stream().filter(item -> item.getColor() == ProductColor.LIGHT).toArray().length;
        int mediumColor = products.stream().filter(item -> item.getColor() == ProductColor.MEDIUM).toArray().length;
        int dark = products.stream().filter(item -> item.getColor() == ProductColor.DARK).toArray().length;

        int small = products.stream().filter(item -> item.getSize() == ProductSize.SMALL).toArray().length;
        int mediumSize = products.stream().filter(item -> item.getSize() == ProductSize.MEDIUM).toArray().length;
        int large = products.stream().filter(item -> item.getSize() == ProductSize.LARGE).toArray().length;



        ///////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////

        request.setAttribute("products", products);
        request.setAttribute("beansCategory", beansCategory);
        request.setAttribute("mugsCategory", mugsCategory);
        request.setAttribute("machinesCategory", machinesCategory);
        request.setAttribute("light", light);
        request.setAttribute("mediumColor", mediumColor);
        request.setAttribute("dark", dark);
        request.setAttribute("small", small);
        request.setAttribute("mediumSize", mediumSize);
        request.setAttribute("large", large);

        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
        dispatcher.forward(request, response);

    }



}
