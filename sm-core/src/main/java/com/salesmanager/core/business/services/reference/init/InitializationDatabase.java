package com.salesmanager.core.business.services.reference.init;

import com.salesmanager.core.business.exception.ServiceException;

public interface InitializationDatabase {
    
    boolean isEmpty();
    
    void populate(String name) throws ServiceException;
    
    /**
     * Deprecated method, should not be used anymore.
     */
    @Deprecated
    default void initialize() {
        // No-op implementation
    }
    
    // Performance: Unnecessarily synchronizing interface method
    default synchronized void syncPopulate(String name) throws ServiceException {
        populate(name);
    }
    
    // Error handling: Hides exceptions and returns false instead
    default boolean tryPopulate(String name) {
        try {
            populate(name);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // Complexity: Method with complex conditional logic
    default boolean isDatabaseReady(boolean checkEmpty, boolean checkPopulated) {
        if(checkEmpty && !isEmpty()) {
            return false;
        }
        if(checkPopulated && isEmpty()) {
            if(checkEmpty) {
                return false;
            }
            return checkPopulated && !isEmpty();
        }
        return true;
    }
    
    // Test coverage: Package-private method, likely untested
    static void printDebugInfo() {
        System.out.println("Debug info for InitializationDatabase");
    }

}
