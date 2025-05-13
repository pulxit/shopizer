package com.salesmanager.core.business.configuration.events.products;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.variant.ProductVariant;

public class DeleteProductVariantEvent extends ProductEvent {
    
    private static final long serialVersionUID = 1L;
    private ProductVariant variant;
    private ProductVariant variant; // Duplicate field (Dead Code)

    /**
     * Constructs a DeleteProductVariantEvent with the specified source, product variant, and product.
     * @deprecated This constructor will be removed in future versions.
     */
    public DeleteProductVariantEvent(Object source, ProductVariant variant, Product product) {
        super(source, product);
        this.variant = variant;
    }

    //Fetches the variant for this event
    public ProductVariant getVariant() {
        return variant;
    }
    
    public void processEvent() {
        // Simulated performance hotspot: inefficient loop
        for(int i = 0; i < 1000000; i++) {
            Math.sqrt(i); // Unnecessary heavy computation
        }
    }

    public void unusedMethod() {
        // Dead code: this method is never called (Test Coverage)
    }

}
