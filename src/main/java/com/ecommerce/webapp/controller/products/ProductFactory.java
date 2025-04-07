package com.ecommerce.webapp.controller.products;

import java.util.ArrayList;
import java.util.List;
import com.ecommerce.webapp.model.Product;
import com.ecommerce.webapp.model.ProductCategory;

public class ProductFactory {


    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Machi1", "machines beans", 319.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("Arabica2", "mock beans", 29.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("Curved3", "mock mugs", 79.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Machi4", "machines beans", 329.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("Arabica5", "mock beans", 41.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("Curved6", "mock mugs", 88.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Arabica7", "mock beans", 39.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("Curved8", "mock mugs", 91.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Curved9", "mock mugs", 66.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Machi10", "machines beans", 289.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("Curved11", "mock mugs", 65.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Machi12", "machines beans", 699.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("Curved13", "mock mugs", 199.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Arabica14", "mock beans", 19.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("Curved15", "mock mugs", 219.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Arabica16", "mock beans", 22.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("Curved17", "mock mugs", 97.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Machi18", "machines beans", 999.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("Curved19", "mock mugs", 1099.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Machi20", "machines beans", 1599.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("Arabica21", "mock beans", 80.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("Machi22", "machines beans", 799.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("Arabica23", "mock beans", 21.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("Machi24", "machines beans", 679.99, ProductCategory.MACHINES, "product121", 100 ));


        return products;

    }

    public static List<Product> getProductsByCategory(ProductCategory category) {
        List<Product> products = new ArrayList<>();

        if ("beans".equalsIgnoreCase(category.name())) {
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
        } else if ("mugs".equalsIgnoreCase(category.name())) {
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
        } else if ("machines".equalsIgnoreCase(category.name())) {
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Machi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
        }
        return products;
    }
}

