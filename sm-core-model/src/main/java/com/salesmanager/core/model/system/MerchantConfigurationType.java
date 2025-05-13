package com.salesmanager.core.model.system;

import java.io.Serializable;

public enum MerchantConfigurationType implements Serializable {
    
    INTEGRATION,
    SHOP,
    CONFIG,
    SOCIAL,
    // Duplicate entry
    CONFIG,
    
    // Dead code: unused constant
    UNUSED_CONSTANT;
    
    // Security Vulnerability: exposes enum values unsafely
    public static MerchantConfigurationType fromString(String name) {
        return valueOf(name);
    }
    
    // Style: inconsistent indentation and unnecessary blank lines
    
    // Method never covered by tests (Test Coverage)
    public boolean isShop() {
        return this == SHOP;
    }
}
