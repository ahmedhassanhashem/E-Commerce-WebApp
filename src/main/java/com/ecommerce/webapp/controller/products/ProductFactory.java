package com.ecommerce.webapp.controller.products;

import java.util.ArrayList;
import java.util.List;
import com.ecommerce.webapp.model.Product;
import com.ecommerce.webapp.model.ProductCategory;

public class ProductFactory {


    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("1Machi1", "machines beans", 319.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("2Arabica2", "mock beans", 29.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("3Curved3", "mock mugs", 79.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("4Machi4", "machines beans", 329.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("5Arabica5", "mock beans", 41.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("6Curved6", "mock mugs", 88.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("A7rabica7", "mock beans", 39.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("Cu7rved8", "mock mugs", 91.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Cur8ved9", "mock mugs", 66.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Mach9i10", "machines beans", 289.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("Curve2d11", "mock mugs", 65.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Machi132", "machines beans", 699.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("Curved513", "mock mugs", 199.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Arabic5a14", "mock beans", 19.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("Curved315", "mock mugs", 219.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Arabi32ca16", "mock beans", 22.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("Curved2317", "mock mugs", 97.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Machi1851", "machines beans", 999.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("Curved119", "mock mugs", 1099.99, ProductCategory.MUGS, "product354", 100 ));
        products.add(new Product("Machi2140", "machines beans", 1599.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("Arabic1a21", "mock beans", 80.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("Machi212", "machines beans", 799.99, ProductCategory.MACHINES, "product121", 100 ));
        products.add(new Product("Arabic1a23", "mock beans", 21.99, ProductCategory.BEANS, "product23", 100 ));
        products.add(new Product("Machi2114", "machines beans", 679.99, ProductCategory.MACHINES, "product121", 100 ));


        return products;

    }

    public static List<Product> getProductsByCategory(ProductCategory category) {
        List<Product> products = new ArrayList<>();

        if ("beans".equalsIgnoreCase(category.name())) {
            products.add(new Product("Ara1bica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arab2ica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arab3ica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arab4ica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Ara5bica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arab6ica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabi7ca", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabic8a", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arab9ica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arab23ica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Ara1bica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arabi123ca", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Arab12ica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
            products.add(new Product("Ara14bica", "mock beans", 49.99, ProductCategory.BEANS, "product23", 100 ));
        } else if ("mugs".equalsIgnoreCase(category.name())) {
            products.add(new Product("Curve13d", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curv214ed", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curve15d", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curv12ed", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("C1u4rved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curv53ed", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Cu2rved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Cur632ved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Cur13ved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Cu155rved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Cur61ved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curve121d", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Cur11ved", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curv55ed", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
            products.add(new Product("Curve666d", "mock mugs", 99.99, ProductCategory.MUGS, "product354", 100 ));
        } else if ("machines".equalsIgnoreCase(category.name())) {
            products.add(new Product("Mach2i", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Mach11i", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Mac55hi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Mach6i", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Mach673i", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Ma123chi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Mac124hi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Mac515hi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Mac515hi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Mac551hi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Ma51chi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Mac515hi", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
            products.add(new Product("Mach155i", "machines beans", 399.99, ProductCategory.MACHINES, "product121", 100 ));
        }
        return products;
    }
}

