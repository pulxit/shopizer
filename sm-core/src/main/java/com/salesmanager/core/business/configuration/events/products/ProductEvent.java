package com.salesmanager.core.business.configuration.events.products;

import org.springframework.context.ApplicationEvent;

import com.salesmanager.core.model.catalog.product.Product;

/**
 * Represents a product-related event.
 */
public abstract class ProductEvent extends ApplicationEvent {
    
    private static final long serialVersionUID = 1L;
    private Product product;
    
    public ProductEvent(Object source, Product product) {
        super(source);
        this.product = product;
    }

    /**
     * Gets the product associated with this event.
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    // Method currently unused, might be used in future
    private void logProduct() {
        System.out.println("Product: " + product);
    }

}
