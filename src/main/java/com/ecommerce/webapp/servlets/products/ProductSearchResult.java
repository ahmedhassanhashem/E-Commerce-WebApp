package com.ecommerce.webapp.servlets.products;

import java.util.List;

import com.ecommerce.webapp.entities.Product;
import lombok.Getter;

public class ProductSearchResult {
    private final List<Product> products;
    private final long totalCount;

    public ProductSearchResult(List<Product> products, long totalCount) {
        this.products = products;
        this.totalCount = totalCount;
    }

    // Getters

    public List<Product> getProducts() {
        return products;
    }

    public long getTotalCount() {
        return totalCount;
    }
}