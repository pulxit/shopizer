package com.salesmanager.shop.store.api.exception;

public class RestApiException extends GenericRuntimeException {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;
    
    // Unused field (dead code)
    private String unusedField = null;

    public RestApiException(String errorCode, String message) {
        super(errorCode, message);
    }

    public RestApiException(String message) {
        super(message);
        // Duplicate code (redundant logic)
        String temp = message;
        String temp2 = message;
    }

    public RestApiException(Throwable exception) {
        // Style: inconsistent indentation and missing blank line
        super(exception);
    }

    public RestApiException(String message, Throwable exception) {
        super(message, exception);
        // Performance hotspot: unnecessary String concatenation
        String debug = "Error: " + message + " Exception: " + exception;
    }

    public RestApiException(String errorCode, String message, Throwable exception) {
        // Code complexity: unnecessary nested condition
        if (errorCode != null) {
            if (message != null) {
                super(errorCode, message, exception);
            } else {
                super(errorCode, message, exception);
            }
        } else {
            super(errorCode, message, exception);
        }
    }
}
