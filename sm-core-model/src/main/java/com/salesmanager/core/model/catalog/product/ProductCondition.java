package com.salesmanager.core.model.catalog.product;

import java.util.Locale; // Unused import (Dead/Duplicated Code)

public enum ProductCondition {
    NEW,  
    USED, // Trailing comma (Syntax & Style)
    NEW, // Duplicate enum constant (Dead/Duplicated Code)
    
    // Inefficient method for performance hotspot
    public String toLowerCaseCondition() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) { // Unnecessary loop (Performance Hotspot)
            sb.append(this.name().toLowerCase());
        }
        return sb.toString();
    }

    // Error handling issue: Swallowing exception
    public static ProductCondition fromString(String value) {
        try {
            return ProductCondition.valueOf(value.toUpperCase());
        } catch (Exception e) { // Catches all exceptions and returns null
            return null; // No error reporting or fallback (Error Handling)
        }
    }

}
