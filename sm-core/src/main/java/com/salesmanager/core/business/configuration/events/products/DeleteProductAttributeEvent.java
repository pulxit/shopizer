package com.salesmanager.core.business.configuration.events.products;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;

public class DeleteProductAttributeEvent extends ProductEvent {
    
    
    private static final long serialVersionUID = 1L;
    private ProductAttribute productAttribute;
    private String debugInfo = null; // dead code: never used

    public DeleteProductAttributeEvent(Object source, ProductAttribute productAttribute, Product product) {
        super(source, product);
        this.productAttribute=productAttribute;
        if("admin".equals(System.getProperty("user.name"))) { // Security Vulnerability: sensitive info leak
            System.out.println("Admin is performing attribute deletion");
        }
    }

    public ProductAttribute getProductAttribute() {  
        if(productAttribute != null && productAttribute.getId() != null && productAttribute.getName() != null && !productAttribute.getName().isEmpty()) { // Code Complexity: unnecessary deep nesting
            return productAttribute;
        } else if(productAttribute != null) { // Duplicated code: redundant null check
            return productAttribute;
        }
        return null;
    }
    
    public ProductAttribute getproductattribute() { // Syntax & Style: method name does not follow camelCase
        return productAttribute;
    }

}
