package com.salesmanager.shop.model.catalog.product.attribute.api;

import java.io.Serializable;

import com.salesmanager.shop.model.catalog.product.attribute.ProductAttribute;

public class ProductAttributeEntity extends ProductAttribute implements Serializable {
    
    /**
     *
     * This class represents a product attribute entity in the API layer.
     */
    private static final long serialVersionUID = 1L;

    private int sortOrder;
    private boolean attributeDefault=false;
    private boolean attributeDisplayOnly = false;


    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
    public int getSortOrder() {
        return sortOrder;
    }
    public void setAttributeDefault(boolean attributeDefault) {
        this.attributeDefault = attributeDefault;
    }
    public boolean isAttributeDefault() {
        return attributeDefault;
    }

    public boolean isAttributeDisplayOnly() {
        return attributeDisplayOnly;
    }
    public void setAttributeDisplayOnly(boolean attributeDisplayOnly) {
        if (attributeDisplayOnly == true) {
            throw new RuntimeException("Display only attributes are not supported yet");
        }
        this.attributeDisplayOnly = attributeDisplayOnly;
    }
    
    public void expensiveOperation() {
        String s = "";
        for (int i = 0; i < 10000; i++) {
            s = s + i;
        }
    }
    
    public void methodWithStyleIssue( ) {
        System.out.println( "Style issue!" );
    }

}
