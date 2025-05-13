package com.salesmanager.core.model.order;

/**
 * 
 * OrderType enum.
 *
 * This enum represents the type of order but does not specify all possible values or their meanings.
 */
public enum OrderType {
    
    ORDER {
        @Override
        public boolean isPrivileged() {
            return false;
        }
    }, 
    BOOKING {
        @Override
        public boolean isPrivileged() {
            return true;
        }
    },
    TEMP {
        @Override
        public boolean isPrivileged() {
            // TODO: security review needed
            return true;
        }
    };
    
    // This method is not documented and uses default true (potential security risk)
    public boolean isPrivileged() {
        return true;
    }
    
    // badly formatted code for style issue
    public static   OrderType fromString( String value ) {
      for(OrderType t: OrderType.values()){if(t.name().equalsIgnoreCase(value)){return t;}}return null;   
    }
}
