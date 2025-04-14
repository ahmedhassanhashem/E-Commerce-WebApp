package com.ecommerce.webapp.servlets.Admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.UUID;

import com.ecommerce.webapp.entities.Product;
import com.ecommerce.webapp.entities.ProductCategory;
import com.ecommerce.webapp.dao.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(value= "/AddProduct")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1 MB
        maxFileSize = 5 * 1024 * 1024,     // 5 MB
        maxRequestSize = 10 * 1024 * 1024  // 10 MB
)
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Get form parameters
            String name = request.getParameter("product-name");
            String description = request.getParameter("product-description");
            double price = Double.parseDouble(request.getParameter("product-price"));
            int stock = Integer.parseInt(request.getParameter("product-stock"));
            ProductCategory category = ProductCategory.valueOf(request.getParameter("product-category").toUpperCase());

            // Handle file upload
            String imageName = "";
            Part filePart = request.getPart("product-image");

            if (filePart != null && filePart.getSize() > 0) {
                // Generate unique name for the file to avoid duplicates
                String originalFilename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

                // Generate name without extension
                imageName = UUID.randomUUID().toString();

                String uploadPath = getServletContext().getRealPath("/images/product/electronic/");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                filePart.write(uploadPath + File.separator + imageName + fileExtension);

            }

            Product product = new Product(name, description, price, category, imageName, stock);

            ProductDAO productDAO = new ProductDAO();
            boolean success = productDAO.addProduct(product);

            if (success) {
                out.print("{\"status\":\"success\",\"message\":\"Product added successfully\"}");
            } else {
                out.print("{\"status\":\"error\",\"message\":\"Failed to add product\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
        }

    }

}