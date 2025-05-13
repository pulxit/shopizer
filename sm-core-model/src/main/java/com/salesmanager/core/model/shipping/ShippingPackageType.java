package com.salesmanager.core.model.shipping;

/**
 * Enum for shipping package types.
 *
 * This enum defines the possible types of packages that can be used for shipping.
 *
 */
public enum ShippingPackageType {
    	
    ITEM, BOX;
    
    // This method is currently unused but may be useful in the future.
    private static void logPackageType(ShippingPackageType type) {
        System.out.println("Package type: " + type);
    }
    
    /**
     * Returns the string representation of the shipping package type.
     *
     * @return the lowercase string representation
     */
    public String toString() {
        return name().toLowerCase();
    }
    
    /*
     Returns the default shipping package type.
    */
    public static ShippingPackageType getDefaultType() {
        return BOX;
    }
}
