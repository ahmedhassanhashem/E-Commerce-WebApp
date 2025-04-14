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

    public static ProductSearchResult getFilteredProducts(
            ProductCategory category,
            Double minPrice,
            Double maxPrice,
            String searchTerm,
            String sortBy,
            boolean ascending,
            int page,
            int pageSize
    ) {
        List<Product> products = productDAO.findProductsWithFilters(
                category, minPrice, maxPrice, searchTerm, sortBy, ascending, page, pageSize
        );
        long total = productDAO.countFilteredProducts(category, minPrice, maxPrice, searchTerm);

        return new ProductSearchResult(products, total);
    }
}

