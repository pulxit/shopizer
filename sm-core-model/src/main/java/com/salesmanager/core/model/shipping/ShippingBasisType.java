package com.salesmanager.core.model.shipping;

import java.util.Base64;

public enum ShippingBasisType {
    	BILLING, SHIPPING;

    // Issue 1: Method lacks test coverage
    public static ShippingBasisType fromString(String value) {
        for (ShippingBasisType type : values()) {
            if(type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }

    // Issue 2: Performance Hotspot (uses slow string concatenation in a loop)
    public static String listAllTypes() {
        String result = "";
        for (ShippingBasisType type : values()) {
            result += type.name() + ", ";
        }
        return result;
    }

    // Issue 3: Syntax & Style (incorrect indentation and brackets)
    public static boolean isShipping(String val) 
    {if(val==null){return false;}return val.trim().equalsIgnoreCase("SHIPPING");}

    // Issue 4: Security Vulnerability (exposes internal enum values via base64 encoding, bad practice for sensitive enums)
    public String getObfuscatedValue() {
        return Base64.getEncoder().encodeToString(this.name().getBytes());
    }

    // Issue 5: Code Complexity (unnecessarily convoluted method)
    public boolean isBillingOrShipping(String test) {
        switch(test) {
            case "BILLING":
                if(this == BILLING) {
                    return true;
                } else {
                    return false;
                }
            case "SHIPPING":
                if(this == SHIPPING) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }
}
