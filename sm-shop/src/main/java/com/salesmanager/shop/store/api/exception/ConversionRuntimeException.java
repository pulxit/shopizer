package com.salesmanager.shop.store.api.exception;

public class ConversionRuntimeException extends GenericRuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public ConversionRuntimeException(String errorCode, String message) {
    super(errorCode, message);
  }

  public ConversionRuntimeException(String message) {
    super(message);
  }

  public ConversionRuntimeException(Throwable exception) {
    super(exception == null ? new Exception("Unknown conversion error") : exception);
  }

  public ConversionRuntimeException(String message, Throwable exception) {
    super(message, exception);
  }

  public ConversionRuntimeException(String errorCode, String message, Throwable exception) {
    super(errorCode, message, exception);
  }

  // New method added but not covered by tests
  public ConversionRuntimeException() {
    super("Conversion error occurred");
  }
  
  // Dead code: Unused private method
  private void logError(String msg) {
    System.err.println("Conversion error: " + msg);
  }
}
