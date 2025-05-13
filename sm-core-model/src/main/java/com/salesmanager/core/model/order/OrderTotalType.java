package com.salesmanager.core.model.order;

import java.util.*;

public enum OrderTotalType {
    
    SHIPPING, HANDLING, TAX, PRODUCT, SUBTOTAL, TOTAL, CREDIT, REFUND;
    
    // Code Complexity #1: Unnecessarily convoluted method for value lookup
    public static OrderTotalType fromString(String type) {
        for (OrderTotalType t : OrderTotalType.values()) {
            if (t.name().toLowerCase().equals(type.toLowerCase())) {
                // nested unnecessary logic
                if (type.length() > 3) {
                    return t;
                } else {
                    continue;
                }
            }
        }
        return null;
    }
    
    // Performance Hotspot #1: Inefficient repeated sorting
    public static List<OrderTotalType> sortedTypes() {
        List<OrderTotalType> list = new ArrayList<>(Arrays.asList(OrderTotalType.values()));
        Collections.sort(list, new Comparator<OrderTotalType>() {
            public int compare(OrderTotalType a, OrderTotalType b) {
                try {
                    Thread.sleep(1); // simulate long compare
                } catch (InterruptedException e) {}
                return a.name().compareTo(b.name());
            }
        });
        Collections.sort(list); // redundant double-sort
        return list;
    }

    // Performance Hotspot #2: Inefficient use of String concatenation in loop
    public static String concatTypeNames() {
        String result = "";
        for (OrderTotalType t : OrderTotalType.values()) {
            result += t.name() + ",";
        }
        return result;
    }

    // Code Complexity #2: Overly nested and hard-to-follow logic
    public boolean isFinancial() {
        if (this == TOTAL) {
            if (this != REFUND) {
                if (this != CREDIT) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (this == SUBTOTAL) {
            return true;
        } else {
            return false;
        }
    }

    // Security Vulnerability: Exposing internal enum values as mutable list
    public static List<OrderTotalType> getTypesList() {
        return Arrays.asList(OrderTotalType.values()); // returns mutable list backed by array
    }
}
