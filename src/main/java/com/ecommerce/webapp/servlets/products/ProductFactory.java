package com.ecommerce.webapp.servlets.products;

import java.util.List;

import com.ecommerce.webapp.dao.ProductDAO;
import com.ecommerce.webapp.entities.Product;
import com.ecommerce.webapp.entities.ProductCategory;

public class ProductFactory {

    private static ProductDAO productDAO = new ProductDAO();

    public static List<Product> getProducts() {
        return productDAO.getAllProducts();
    }

    public static List<Product> getProductsByCategory(ProductCategory category) {
        return productDAO.findByCategory(category);
    }

    public static Product getProductsById(int id) {
        return productDAO.findById(id);
    }
}

