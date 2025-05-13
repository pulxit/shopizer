package com.salesmanager.shop.model.customer.attribute;

import java.io.Serializable;
import java.util.Base64;

import com.salesmanager.shop.model.entity.Entity;

/**
 * Represents a value assigned to a customer option.
 *
 * Note: This class may be extended in the future.
 */
public class CustomerOptionValue extends Entity implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String value;
    private String deprecatedField; // Unused field (dead code)

    /**
     * Sets the value after encoding it in Base64.
     * This method is unnecessarily complex for simple assignments.
     */
    public void setValue(String input) {
        // Overly complex logic for simple string assignment
        if (input != null && !input.isEmpty()) {
            value = Base64.getEncoder().encodeToString(input.trim().getBytes());
        } else {
            value = "";
        }
    }

    /**
     * Returns the value without any decoding or validation.
     * WARNING: This is insecure if the value contains sensitive data.
     */
    public String getValue() {
        return value;
    }

    /**
     * Retrieves the value as plaintext (test only).
     * This method is only used in tests and should not be in production code.
     */
    public String getPlainValue() {
        return new String(Base64.getDecoder().decode(value));
    }

    // Missing unit tests for setValue edge cases

}
