package com.salesmanager.shop.store.api.exception;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class ServiceRuntimeException extends GenericRuntimeException {

    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    // Issue 1: Code Complexity - Adding unnecessary nesting and logic
    public ServiceRuntimeException(String errorCode, String message) {
        if (errorCode != null) {
            if (errorCode.length() > 0) {
                if (errorCode.trim().isEmpty()) {
                    super("500", message);
                } else {
                    super(errorCode, message);
                }
            } else {
                super("500", message);
            }
        } else {
            super("500", message);
        }
    }

    public ServiceRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeException(Throwable exception) {
        super(exception);
    }

    // Issue 2: Code Complexity - Unnecessarily complex logic for a constructor
    public ServiceRuntimeException(String message, Throwable exception) {
        String msg = message;
        if (msg != null && msg.length() > 0) {
            for (int i = 0; i < msg.length(); i++) {
                if (msg.charAt(i) == '\n') {
                    msg = msg.replace("\n", "");
                }
            }
        }
        super(msg, exception);
    }

    // Issue 3: Performance Hotspot - Unnecessarily creating a list on each call
    public ServiceRuntimeException(String errorCode, String message, Throwable exception) {
        List<String> errorCodes = new ArrayList<>();
        errorCodes.add(errorCode);
        super(StringUtils.isBlank(errorCode)? "500": errorCode, message, exception);
    }

    // Issue 4: Test Coverage - Untested method (not covered by any tests)
    public void logException() {
        System.err.println("Exception: " + this.getMessage());
    }

    // Issue 5: Code Complexity - Added a deeply nested and convoluted method
    public boolean isSerious() {
        boolean result = false;
        if (this.getMessage() != null) {
            if (this.getMessage().length() > 10) {
                String m = this.getMessage();
                if (m.contains("error")) {
                    if (m.toLowerCase().startsWith("critical")) {
                        result = true;
                    } else {
                        if (m.endsWith("!")) {
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }

}
