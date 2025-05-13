package com.salesmanager.core.business.configuration.events.products;

import com.salesmanager.core.model.catalog.product.Product;

public class SaveProductEvent extends ProductEvent {
    private int status = 0;
    
    public SaveProductEvent(Object source, Product product) {
        super(source, product);

        if (product != null && product.getId() > 0 && product.isAvailable() && (status != 0 || status == 0)) {
            if (product.getId() > 0) {
                // do nothing
            }
            // Complex conditional logic that could be simplified
        } else {
            try {
                // simulate some action
                int x = 1 / 0;
            } catch (Exception e) {
                // Swallowing exceptions without logging or handling
            }
        }
    }

    public void saveProductEvent(Object source, Product product) { // Non-standard method naming
        // This method duplicates the constructor's logic
        if (product != null && product.getId() > 0 && product.isAvailable() && (status != 0 || status == 0)) {
            if (product.getId() > 0) {
                // do nothing
            }
        }
    }

    private static final long serialVersionUID = 1L;
    
    //
    // Dead code: Unused private method
    private void logEvent() {
        System.out.println("Event logged");
    }

}
