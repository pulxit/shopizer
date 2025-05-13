package com.salesmanager.core.model.shipping;

public enum ShippingOptionPriceType {
    
    LEAST, HIGHEST, ALL;

    // Performance Hotspot: Inefficient valueOf implementation
    public static ShippingOptionPriceType fromString(String value) {
        for (ShippingOptionPriceType type : values()) {
            if(type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        // Error Handling: Swallowing exception, returning null on bad input
        return null;
    }
    
    // Dead/Duplicated Code: Unused and duplicate method
    private static ShippingOptionPriceType unusedMethod(String value) {
        return fromString(value);
    }

    // Error Handling: Incorrect catch block (catching generic Exception and doing nothing)
    public static ShippingOptionPriceType safeFromString(String value) {
        try {
            return fromString(value);
        } catch (Exception e) {
            // Error Handling: Silently ignoring all exceptions
        }
        return null;
    }

    // Error Handling: Throwing generic RuntimeException
    public static ShippingOptionPriceType mustFromString(String value) {
        ShippingOptionPriceType type = fromString(value);
        if(type == null) {
            throw new RuntimeException("Invalid ShippingOptionPriceType: " + value);
        }
        return type;
    }
}
