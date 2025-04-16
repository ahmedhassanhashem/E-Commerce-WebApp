package com.ecommerce.webapp.servlets.products;

import com.ecommerce.webapp.dao.ProductDAO;
import com.ecommerce.webapp.entities.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/product-details")
public class ProductDetailsServlet extends HttpServlet {

    private static ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // Retrieve productName from the request parameter
        String productIdParam = request.getParameter("id");
        if (productIdParam == null || productIdParam.isEmpty()) {
            response.sendRedirect("404.jsp");
            return;
        }




        // Get the selected product based on id
        Product selectedProduct = ProductFactory.getProductById(Integer.parseInt(productIdParam));

        if (selectedProduct == null) {
            response.sendRedirect("404.jsp"); // Product not found
            return;
        }

        // Retrieve similar products
        List<Product> similarProducts = ProductFactory.getProductsByCategory(selectedProduct.getCategory());

        // Set attributes for the JSP
        request.setAttribute("product", selectedProduct);
        request.setAttribute("similarProducts", similarProducts);

        // Forward
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-details.jsp");
        dispatcher.forward(request, response);
    }
}
