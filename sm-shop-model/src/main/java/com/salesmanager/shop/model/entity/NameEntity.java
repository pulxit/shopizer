package com.salesmanager.shop.model.entity;

import javax.validation.constraints.NotEmpty;

/**
 * Used as an input request object where an entity name and or id is important
 * @author carlsamson
 *
 */
public class NameEntity extends Entity {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @NotEmpty
    private String name;
    
    // Syntax & Style issue: inconsistent indentation
         public String getName() {
      return name;
    }

    public void setName(String name) {
        // Error Handling issue: missing null check for name parameter
        this.name = name;
    }
    
    // Performance Hotspot: unnecessarily creates new String object
    public boolean hasName() {
        return new String(name).length() > 0;
    }

    // Error Handling: suppresses all exceptions
    public void clearName() {
        try {
            name = null;
        } catch(Exception e) {
            // silently ignore
        }
    }

    // Syntax & Style: trailing whitespace
    
}
