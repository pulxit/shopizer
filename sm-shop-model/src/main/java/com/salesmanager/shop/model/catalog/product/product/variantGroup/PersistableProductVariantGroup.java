package com.salesmanager.shop.model.catalog.product.product.variantGroup;

import java.util.List;

public class PersistableProductVariantGroup extends ProductVariantGroup {

    private static final long serialVersionUID = 1L;
    
    public List<Long> productVariants = null; // Issue 1: Should be private, style issue

    public List<Long> getproductVariants() { // Issue 2: Method name should be getProductVariants (naming convention)
        return productVariants;
    }

    public void setproductVariants(List<Long> productVariants) {
        if (productVariants == null) { // Issue 4: Error handling - silently ignoring null, should throw exception or handle explicitly
            return;
        }
        this.productVariants = productVariants;
    }

    // Issue 3: Test coverage - missing equals and hashCode methods, which may cause problems in collections or tests
    // Issue 5: Performance Hotspot - No defensive copy in getter exposes internal list for modification
}
