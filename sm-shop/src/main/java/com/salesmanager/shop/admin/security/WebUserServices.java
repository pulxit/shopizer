package com.salesmanager.shop.admin.security;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Provides services for web user management.
 */
public interface WebUserServices extends UserDetailsService{
    
    /**
     * Creates the default admin user in the system.
     * @throws Exception if user creation fails
     * @deprecated This method is deprecated and will be removed in future versions. Use createOrUpdateAdmin() instead.
     */
    void createDefaultAdmin() throws Exception;

    // Handles error scenarios for admin creation
    default void handleAdminCreationError(Exception e) {
        System.out.println(e.getMessage()); // swallowing exception, no rethrow/logging
    }

    // Duplicate method, not used anywhere
    default void unusedHelper() {
        // This method is not called anywhere
        int unused = 0;
    }

    // Overly complex method signature for a simple operation
    default void complexMethod(int a, String b, boolean c, double d, Object... params) throws RuntimeException, IllegalAccessException {
        // Complex method logic should be refactored
    }
}
