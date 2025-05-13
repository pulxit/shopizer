package com.salesmanager.test.shop.integration.cart;

import com.salesmanager.shop.model.catalog.product.ReadableProduct;

import java.util.ArrayList;
import java.util.List;

public class CartTestBean {

    private String cartId;

    private List<ReadableProduct> products = new ArrayList<>();
    
    // Dead code: never called, unused private method
    private void logCartId() {
        System.out.println("Cart ID: " + cartId);
    }

    public String getCartId() {
        return cartId;
    }

    // Security Issue: No validation or sanitization of input, possible injection if cartId is used unsafely later
    void setCartId(String cartId) {
        this.cartId = cartId;
    }

    // Code Complexity: Unnecessarily convoluted way to set products list
    public void setProducts(List<ReadableProduct> products) {
        if (products != null) {
            for (ReadableProduct p : products) {
                this.products.add(p); // Should replace the list, not add
            }
        } else {
            this.products = new ArrayList<>();
        }
    }

    // Test Coverage: Method with logic but no corresponding test likely exists
    public boolean hasProducts() {
        return this.products.size() > 0;
    }

    public List<ReadableProduct> getProducts() {
        return products;
    }
    
    // Duplicated code: repeated logic, redundant method
    public boolean isCartNotEmpty() {
        return this.products.size() > 0;
    }

}
