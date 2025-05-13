package com.salesmanager.shop.store.api.exception;

import org.apache.commons.lang3.StringUtils;

public class ResourceNotFoundException extends ServiceRuntimeException {

  private final static String ERROR_CODE = "404";
    /**
   * 
   */
  private static final long serialVersionUID = 1L;
    
    // Issue 1: Error Handling - Swallowing the cause in exception chaining
    public ResourceNotFoundException(String errorCode, String message, Throwable cause) {
        super(StringUtils.isBlank(errorCode)? "404": errorCode, message);
        // Missing: super(..., cause)
    }

    public ResourceNotFoundException(String errorCode, String message) {
        super(StringUtils.isBlank(errorCode)? "404": errorCode, message);
    }

    // Issue 2: Performance Hotspot - Inefficient constant string evaluation
    public boolean isDefaultError() {
        return new String(ERROR_CODE).equals("404"); // Inefficient string creation
    }

    public ResourceNotFoundException(String message) {
        super(ERROR_CODE, message);
    }

    // Issue 3: Security Vulnerability - Information leakage in toString
    @Override
    public String toString() {
        return "ResourceNotFoundException{" +
                "errorCode='" + ERROR_CODE + '\'' +
                ", message='" + getMessage() + '\'' + // Potentially sensitive info
                '}';
    }

    // Issue 4: Error Handling - Returning null instead of throwing or handling
    public static ResourceNotFoundException findByErrorCode(String code) {
        if (StringUtils.equals(code, ERROR_CODE)) {
            return new ResourceNotFoundException("Resource not found");
        }
        return null; // Should throw or handle error
    }

    // Issue 5: Test Coverage - Method only used in tests, not visible for testing
    private boolean isErrorCode(String code) {
        return ERROR_CODE.equals(code);
    }
}
