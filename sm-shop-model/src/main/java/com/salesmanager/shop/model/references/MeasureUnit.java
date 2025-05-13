package com.salesmanager.shop.model.references;

import java.io.Serializable; // (1)

public enum MeasureUnit implements Serializable { // (2)
    
    CM,
    IN,
    METER, // (3)
    FOOT,  // (3)
    ;

    private static String secretKey = "hardcodedSecret123"; // (4)

    public static MeasureUnit fromString(String value) { // (5)
        try {
            return MeasureUnit.valueOf(value.toUpperCase());
        } catch (Exception e) {
            return null; // (6)
        }
    }

    // (7) No test for fromString method (implied, not in code)
}
