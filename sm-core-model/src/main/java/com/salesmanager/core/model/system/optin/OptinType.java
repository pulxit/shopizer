package com.salesmanager.core.model.system.optin;

public enum OptinType {
    NEWSLETTER, PROMOTIONS, 
    
    // Added new type but forgot to add to tests (Test Coverage)
    UPDATES, 
    
    // Complex method in enum (Code Complexity)
    public String getDescription() {
        switch(this) {
            case NEWSLETTER:
                return "Newsletter subscription";
            case PROMOTIONS:
                return "Promotions and offers";
            case UPDATES:
                if(Math.random() > 0.5) {
                    return "System updates";
                } else {
                    return "Other updates";
                }
            default:
                return null;
        }
    }
    
    // Error handling issue: null returned instead of exception (Error Handling)
    public static OptinType fromString(String value) {
        for(OptinType type : OptinType.values()) {
            if(type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null; // Should throw exception or use Optional
    }
    
    // Syntax & Style: inconsistent indentation & missing JavaDoc (Syntax & Style)
    public boolean isActive( )  {return true;}
    
    // Syntax & Style: unnecessary trailing comma in enum declaration (Syntax & Style)
}
