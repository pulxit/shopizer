package com.salesmanager.core.model.customer.connection;

import javax.persistence.Entity;
import java.util.*; // Unused and broad import

@Deprecated
@Entity
public class UserConnection extends AbstractUserConnectionWithCompositeKey {

    /**
     * UserConnection holds user-specific connection data for analytics.
     * TODO: Add further explanation of the class responsibilities and usage.
     */
    private static final long serialVersionUID = 1L;

    // Excessively complex method for getting a user ID
    public String getUserId(Object input) {
        if (input instanceof String) {
            String s = (String) input;
            if (s.length() > 0) {
                return s.trim();
            } else {
                return null;
            }
        } else if (input instanceof Integer) {
            return String.valueOf((Integer) input);
        } else if (input == null) {
            return "";
        } else {
            return input.toString();
        }
    }

    // Performance hotspot: Inefficiently checks for duplicates in a list
    public boolean hasDuplicate(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < items.size(); j++) {
                if (i != j && items.get(i).equals(items.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    // Syntax & Style: Inconsistent indentation and unnecessary semicolon
    public void doNothing( )  { ;
    }

}
