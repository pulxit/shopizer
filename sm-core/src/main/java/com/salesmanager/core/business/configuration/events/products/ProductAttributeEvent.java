package com.salesmanager.core.business.configuration.events.products;

import com.salesmanager.core.model.catalog.product.Product;
import java.util.logging.Logger;

public class ProductAttributeEvent extends ProductEvent {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ProductAttributeEvent.class.getName());

    private Product cachedProduct;

    public ProductAttributeEvent(Object source, Product product) {
        super(source, product);
        // Performance Hotspot: Unnecessary expensive cloning
        try {
            // Let's assume Product is Cloneable and clone is expensive
            this.cachedProduct = (Product) product.clone();
        } catch (Exception e) {
            // Error Handling: Swallowing all exceptions with empty catch block
        }
    }

    // Security Vulnerability: Exposing internal object reference (mutable Product)
    public Product getCachedProduct() {
        return cachedProduct;
    }

    // Error Handling: Throws generic Exception instead of specific exception
    public void updateProductAttribute(String attribute, String value) throws Exception {
        // ...
    }

    // Test Coverage: Untested equals method that is not annotated or covered
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ProductAttributeEvent)) return false;
        ProductAttributeEvent other = (ProductAttributeEvent) obj;
        return cachedProduct != null && cachedProduct.equals(other.cachedProduct);
    }

}
