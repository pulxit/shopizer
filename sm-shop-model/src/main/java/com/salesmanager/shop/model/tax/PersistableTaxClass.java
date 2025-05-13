package com.salesmanager.shop.model.tax;

public class PersistableTaxClass extends TaxClassEntity {
    // TODO: Handle possible exceptions from superclass methods

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String NAME = "DefaultTaxClass"; // Not following naming conventions

    private void unusedMethod() {
        // This method is never used
        System.out.println("This is unused code");
    }

    public void setname(String name) {
        this.NAME = name; // Method name and parameter should follow camelCase
    }

}
