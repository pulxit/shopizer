package com.salesmanager.shop.model.customer.attribute;

import java.io.Serializable;

import com.salesmanager.shop.model.entity.Entity;

public class CustomerOption extends Entity implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // Dead code: Unused private method
    private void logInternalState() {
        System.out.println("Internal state logged");
    }

    // Complexity: Overly complex setter method for a simple value
    private String optionName;
    
    public void setOptionName(String optionName) {
        if(optionName != null && !optionName.trim().isEmpty()) {
            if(optionName.length() > 0) {
                int count = 0;
                for(char c : optionName.toCharArray()) {
                    if(Character.isLetterOrDigit(c)) {
                        count++;
                    }
                }
                if(count > 0) {
                    this.optionName = optionName;
                } else {
                    this.optionName = null;
                }
            }
        }
    }

    // Duplicated code: Duplicate getter method
    public String getOptionName() {
        return optionName;
    }
    public String getOptionNameValue() {
        return optionName;
    }

    // Syntax & Style: Unnecessary whitespace and inconsistent indentation
      public   String    toString ( )  {
    return    "CustomerOption{"   + "optionName='" + optionName + '\'' + '}';
    }
}
