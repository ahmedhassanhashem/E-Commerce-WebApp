// package com.ecommerce.webapp.servlets.Admin;

// import com.ecommerce.webapp.dao.ProductDAO;
// import com.ecommerce.webapp.entities.Product;
// import com.ecommerce.webapp.entities.ProductCategory;
// import com.google.gson.Gson;
// import com.google.gson.JsonObject;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import java.io.IOException;
// import java.io.PrintWriter;

// @WebServlet("/updateProduct")
// public class UpdateProductServlet extends HttpServlet {
    
//     private ProductDAO productDAO = new ProductDAO();
//     private Gson gson = new Gson();
    
//     @Override
//     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         response.setContentType("application/json");
//         PrintWriter out = response.getWriter();
//         JsonObject jsonResponse = new JsonObject();
        
//         try {
//             // Get parameters
//             int productId = Integer.parseInt(request.getParameter("productId"));
//             String name = request.getParameter("name");
//             double price = Double.parseDouble(request.getParameter("price"));
//             int stock = Integer.parseInt(request.getParameter("stock"));
//             String categoryStr = request.getParameter("category");
//             String description = request.getParameter("description");
//             String image = request.getParameter("image");
            
//             // Get existing product
//             Product product = productDAO.findById(productId);
//             if (product == null) {
//                 jsonResponse.addProperty("success", false);
//                 jsonResponse.addProperty("message", "Product not found");
//                 out.print(jsonResponse.toString());
//                 return;
//             }
            
//             // Update product properties
//             product.setName(name);
//             product.setPrice(price);
//             product.setStock(stock);
//             product.setCategory(ProductCategory.valueOf(categoryStr));
//             product.setDescription(description);
//             product.setImage(image);
            
//             // Save the updated product
//             boolean updated = productDAO.updateProduct(product);
            
//             if (updated) {
//                 jsonResponse.addProperty("success", true);
//                 jsonResponse.addProperty("message", "Product updated successfully");
//             } else {
//                 jsonResponse.addProperty("success", false);
//                 jsonResponse.addProperty("message", "Failed to update product");
//             }
            
//         } catch (NumberFormatException e) {
//             jsonResponse.addProperty("success", false);
//             jsonResponse.addProperty("message", "Invalid number format");
//         } catch (IllegalArgumentException e) {
//             jsonResponse.addProperty("success", false);
//             jsonResponse.addProperty("message", "Invalid category");
//         } catch (Exception e) {
//             jsonResponse.addProperty("success", false);
//             jsonResponse.addProperty("message", "Error updating product: " + e.getMessage());
//         }
        
//         out.print(jsonResponse.toString());
//     }
// }
// package com.ecommerce.webapp.servlets.Admin;

// import com.ecommerce.webapp.dao.ProductDAO;
// import com.ecommerce.webapp.entities.Product;
// import com.ecommerce.webapp.entities.ProductCategory;
// import com.google.gson.Gson;
// import com.google.gson.JsonObject;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.annotation.MultipartConfig;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.Part;

// import java.io.File;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.nio.file.Paths;
// import java.util.UUID;

// @WebServlet("/updateProduct")
// @MultipartConfig(
//         fileSizeThreshold = 1024 * 1024,  // 1 MB
//         maxFileSize = 5 * 1024 * 1024,     // 5 MB
//         maxRequestSize = 10 * 1024 * 1024  // 10 MB
// )
// public class UpdateProductServlet extends HttpServlet {
    
//     private ProductDAO productDAO = new ProductDAO();
//     private Gson gson = new Gson();
    
//     @Override
//     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         response.setContentType("application/json");
//         PrintWriter out = response.getWriter();
//         JsonObject jsonResponse = new JsonObject();
        
//         try {
//             // Get parameters
//             int productId = Integer.parseInt(request.getParameter("productId"));
//             String name = request.getParameter("name");
//             double price = Double.parseDouble(request.getParameter("price"));
//             int stock = Integer.parseInt(request.getParameter("stock"));
//             String categoryStr = request.getParameter("category");
//             String description = request.getParameter("description");
//             String currentImage = request.getParameter("currentImage");
            
//             // Get existing product
//             Product product = productDAO.findById(productId);
//             if (product == null) {
//                 jsonResponse.addProperty("success", false);
//                 jsonResponse.addProperty("message", "Product not found");
//                 out.print(jsonResponse.toString());
//                 return;
//             }
            
//             // Handle file upload
//             String imageName = currentImage; // Keep current image by default
//             Part filePart = request.getPart("product-image");
            
//             if (filePart != null && filePart.getSize() > 0) {
//                 // Generate unique name for the file to avoid duplicates
//                 String originalFilename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//                 String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                
//                 if (!fileExtension.equalsIgnoreCase(".jpg") && !fileExtension.equalsIgnoreCase(".jpeg")) {
//                     jsonResponse.addProperty("success", false);
//                     jsonResponse.addProperty("message", "Only JPG images are allowed");
//                     out.print(jsonResponse.toString());
//                     return;
//                 }
                
//                 // Generate name without extension
//                 imageName = UUID.randomUUID().toString() + fileExtension;
                
//                 String uploadPath = getServletContext().getRealPath("/images/product/electronic/");
//                 File uploadDir = new File(uploadPath);
//                 if (!uploadDir.exists()) {
//                     uploadDir.mkdirs();
//                 }
                
//                 // Save the new image
//                 filePart.write(uploadPath + File.separator + imageName);
//             }
            
//             // Update product properties
//             product.setName(name);
//             product.setPrice(price);
//             product.setStock(stock);
//             product.setCategory(ProductCategory.valueOf(categoryStr));
//             product.setDescription(description);
//             product.setImage(imageName);
            
//             // Save the updated product
//             boolean updated = productDAO.updateProduct(product);
            
//             if (updated) {
//                 jsonResponse.addProperty("success", true);
//                 jsonResponse.addProperty("message", "Product updated successfully");
//             } else {
//                 jsonResponse.addProperty("success", false);
//                 jsonResponse.addProperty("message", "Failed to update product");
//             }
            
//         } catch (NumberFormatException e) {
//             jsonResponse.addProperty("success", false);
//             jsonResponse.addProperty("message", "Invalid number format");
//         } catch (IllegalArgumentException e) {
//             jsonResponse.addProperty("success", false);
//             jsonResponse.addProperty("message", "Invalid category");
//         } catch (Exception e) {
//             jsonResponse.addProperty("success", false);
//             jsonResponse.addProperty("message", "Error updating product: " + e.getMessage());
//         }
        
//         out.print(jsonResponse.toString());
//     }
// }
package com.ecommerce.webapp.servlets.Admin;

import com.ecommerce.webapp.dao.ProductDAO;
import com.ecommerce.webapp.entities.Product;
import com.ecommerce.webapp.entities.ProductCategory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet("/updateProduct")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1 MB
        maxFileSize = 5 * 1024 * 1024,     // 5 MB
        maxRequestSize = 10 * 1024 * 1024  // 10 MB
)
public class UpdateProductServlet extends HttpServlet {
    
    private ProductDAO productDAO = new ProductDAO();
    private Gson gson = new Gson();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JsonObject jsonResponse = new JsonObject();
        
        try {
            // Get parameters
            int productId = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String categoryStr = request.getParameter("category");
            String description = request.getParameter("description");
            String currentImage = request.getParameter("currentImage");
            
            // Get existing product
            Product product = productDAO.findById(productId);
            if (product == null) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Product not found");
                out.print(jsonResponse.toString());
                return;
            }
            
            // Handle file upload
            // String imageName = currentImage; // Keep current image by default
            // Part filePart = request.getPart("product-image");
            
            // if (filePart != null && filePart.getSize() > 0) {
            //     // Get submitted filename
            //     String originalFilename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                
            //     // Check if it's empty
            //     if (originalFilename == null || originalFilename.isEmpty()) {
            //         // No file was selected, keep the current image
            //     } else {
            //         // Extract file extension
            //         String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                    
            //         // Validate file extension
            //         if (!fileExtension.equals(".jpg") && !fileExtension.equals(".jpeg")) {
            //             jsonResponse.addProperty("success", false);
            //             jsonResponse.addProperty("message", "Only JPG images are allowed");
            //             out.print(jsonResponse.toString());
            //             return;
            //         }
                    
            //         // Generate unique filename
            //         imageName = UUID.randomUUID().toString() + fileExtension;
                    
            //         // Get upload directory path
            //         String uploadPath = getServletContext().getRealPath("/images/product/electronic/");
                    
            //         // Save the file
            //         filePart.write(uploadPath + File.separator + imageName);
                    
            //         // Log the successful upload (optional)
            //         System.out.println("Uploaded file: " + imageName + " to " + uploadPath);
            //     }
            // }
             // Handle file upload
             String imageName = currentImage;
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
            
            // Update product properties
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setCategory(ProductCategory.valueOf(categoryStr));
            product.setDescription(description);
            product.setImage(imageName);
            
            // Save the updated product
            boolean updated = productDAO.updateProduct(product);
            
            if (updated) {
                jsonResponse.addProperty("success", true);
                jsonResponse.addProperty("message", "Product updated successfully");
            } else {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Failed to update product");
            }
            
        } catch (NumberFormatException e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Invalid number format");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Invalid category");
            e.printStackTrace();
        } catch (Exception e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Error updating product: " + e.getMessage());
            e.printStackTrace();
        }
        
        out.print(jsonResponse.toString());
    }
}