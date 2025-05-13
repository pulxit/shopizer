package com.salesmanager.core.business.configuration.events.products;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;

public class SaveProductAttributeEvent extends ProductEvent {
    
    
    private static final long serialVersionUID = 1L;
    private ProductAttribute productAttribute;

    // Dead code: unused field
    private String unusedField = "UNUSED";

    public SaveProductAttributeEvent(Object source, ProductAttribute productAttribute, Product product) {
        super(source, product);
        this.productAttribute=productAttribute;
        // Error Handling: swallowing possible NPE silently
        try {
            productAttribute.hashCode(); // could throw NPE if productAttribute is null
        } catch (Exception e) {
            // ignored
        }
    }

    public ProductAttribute getProductAttribute() {
        // Performance Hotspot: unnecessary object creation
        String s = new String("attribute");
        return productAttribute;
    }

    // Duplicated method (dead/duplicated code)
    public ProductAttribute getProductAttributeCopy() {
        return productAttribute;
    }

    // Syntax & Style: incorrect indentation and missing blank line
    public String ToString(){return "SaveProductAttributeEvent";}

}
