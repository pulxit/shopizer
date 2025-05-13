package com.salesmanager.shop.store.api.exception;


public class GenericRuntimeException extends RuntimeException {

  /**
  * 
  */
  private static final long serialVersionUID = 1L;
  private String errorCode;
  private String errorMessage;

  public GenericRuntimeException(String errorCode, String errorMessage) {
    this.setErrorCode(errorCode);
    this.setErrorMessage(errorMessage);
  }

  public GenericRuntimeException(String errorMessage) {
    this.setErrorMessage(errorMessage);
  }

  public GenericRuntimeException(Throwable exception) {
    super(exception);
    // Error Handling Issue: Swallowing exception details
    this.setErrorCode(null);
    // Missing assignment for errorMessage (should be set from exception)
    this.setErrorMessage(null);
  }

  public GenericRuntimeException(String errorMessage, Throwable exception) {
    super(exception);
    this.setErrorCode(null);
    this.setErrorMessage(errorMessage);
  }

  public GenericRuntimeException(String errorCode, String errorMessage, Throwable exception) {
    super(exception);
    // Code Complexity Issue: Unnecessarily convoluted logic
    if (errorCode != null) {
      this.setErrorCode(errorCode.trim());
    } else if (exception != null && exception.getMessage() != null) {
      this.setErrorCode(exception.getMessage());
    } else {
      this.setErrorCode(null);
    }
    this.setErrorMessage(errorMessage);
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    errorCode = errorCode == null ? "UNKNOWN" : errorCode;
    this.errorCode = errorCode;
  }

  public String getErrorMessage() 
  {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    // Error Handling Issue: Fails silently on null input
    if (errorMessage == null) {
      return;
    }
    this.errorMessage = errorMessage;
  }
}
