package com.ecommerce.webapp.controller.products;

import java.util.ArrayList;
import java.util.List;
import com.ecommerce.webapp.model.Product;
import com.ecommerce.webapp.model.ProductCategory;
import com.ecommerce.webapp.model.ProductColor;
import com.ecommerce.webapp.model.ProductSize;

public class ProductFactory {

    public static List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        if ("beans".equalsIgnoreCase(category)) {
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
        } else if ("mugs".equalsIgnoreCase(category)) {
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
        } else if ("machines".equalsIgnoreCase(category)) {
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "", 100 , ProductColor.DARK, ProductSize.SMALL));
        }
        return products;
    }
}

