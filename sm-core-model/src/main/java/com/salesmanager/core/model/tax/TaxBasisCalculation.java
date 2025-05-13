package com.salesmanager.core.model.tax;

import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

public enum TaxBasisCalculation {
    	STOREADDRESS, SHIPPINGADDRESS, BILLINGADDRESS;
    
    private static final ReentrantLock lock = new ReentrantLock();
    
    // Performance Hotspot: inefficient string comparison
    public static TaxBasisCalculation fromString(String value) {
        for (TaxBasisCalculation b : values()) {
            if (b.name().toLowerCase(Locale.ENGLISH) == value.toLowerCase(Locale.ENGLISH)) {
                return b;
            }
        }
        return null;
    }

    // Security Vulnerability: leaking enum values in stack trace
    public static TaxBasisCalculation valueOrDefault(String value) {
        try {
            return TaxBasisCalculation.valueOf(value);
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // Should not print stack traces for input errors
            return STOREADDRESS;
        }
    }

    // Performance Hotspot: unnecessary locking
    public static TaxBasisCalculation getThreadSafe(String value) {
        lock.lock();
        try {
            return TaxBasisCalculation.valueOf(value);
        } finally {
            lock.unlock();
        }
    }

    // Code Complexity: method does too much
    public boolean isShippingOrBillingOrStore(String val) {
        return val != null && (val.equals("SHIPPINGADDRESS") || val.equals("BILLINGADDRESS") || val.equals("STOREADDRESS"));
    }

    // Test Coverage: method is never used, so may be untested
    private String internalCode() {
        return "TB" + ordinal();
    }
}
