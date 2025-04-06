package com.ecommerce.webapp.controller.products;

import java.util.ArrayList;
import java.util.List;
import com.ecommerce.webapp.model.Product;
import com.ecommerce.webapp.model.ProductCategory;
import com.ecommerce.webapp.model.ProductColor;
import com.ecommerce.webapp.model.ProductSize;

public class ProductFactory {

    public static List<Product> getProductsByCategory(ProductCategory category) {
        List<Product> products = new ArrayList<>();
        if ("beans".equalsIgnoreCase(category.name())) {
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 , ProductColor.LIGHT, ProductSize.MEDIUM));
        } else if ("mugs".equalsIgnoreCase(category.name())) {
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 , ProductColor.MEDIUM, ProductSize.LARGE));
        } else if ("machines".equalsIgnoreCase(category.name())) {
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 , ProductColor.DARK, ProductSize.SMALL));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 , ProductColor.DARK, ProductSize.SMALL));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 , ProductColor.DARK, ProductSize.SMALL));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 , ProductColor.DARK, ProductSize.SMALL));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 , ProductColor.DARK, ProductSize.SMALL));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 , ProductColor.DARK, ProductSize.SMALL));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 , ProductColor.DARK, ProductSize.SMALL));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 , ProductColor.DARK, ProductSize.SMALL));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 , ProductColor.DARK, ProductSize.SMALL));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 , ProductColor.DARK, ProductSize.SMALL));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 , ProductColor.DARK, ProductSize.SMALL));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 , ProductColor.DARK, ProductSize.SMALL));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 , ProductColor.DARK, ProductSize.SMALL));
        }
        return products;
    }
}

