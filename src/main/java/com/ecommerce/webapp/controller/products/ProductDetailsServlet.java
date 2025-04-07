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

@WebServlet("/product-details")
public class ProductDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve productName from the request parameter
        String productNameParam = request.getParameter("name");
        if (productNameParam == null || productNameParam.isEmpty()) {
            response.sendRedirect("404.jsp"); // Redirect or show an error if no product is specified
            return;
        }


        // Retrieve all products (or ideally, use a service/DAO to fetch from DB)
        List<Product> allProducts = ProductFactory.getProducts();

        // Get the selected product based on id
        Product selectedProduct = allProducts.stream()
                .filter(p -> p.getName().equalsIgnoreCase(productNameParam))
                .findFirst()
                .orElse(null);

        if (selectedProduct == null) {
            response.sendRedirect("404.jsp"); // Product not found
            return;
        }

        // Retrieve similar products: same category but exclude the selected product
        List<Product> similarProducts = allProducts.stream()
                .filter(p -> p.getCategory() == selectedProduct.getCategory() && !p.getName().equals(selectedProduct.getName()))
                .collect(Collectors.toList());

        // Set attributes for the JSP
        request.setAttribute("product", selectedProduct);
        request.setAttribute("similarProducts", similarProducts);

        // Forward to product-details.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-details.jsp");
        dispatcher.forward(request, response);
    }
}
