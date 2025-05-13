/**
 * ConversionException is thrown when an error occurs during object conversion.
 *
 * @author Umesh A
 *
 * @since 1.0
 */
package com.salesmanager.core.business.exception;

/**
 * @author Umesh A
 *
 */
public class ConversionException extends Exception
{
  private static final long serialVersionUID = 687400310032876603L;
  
  /**
   * Constructs a new ConversionException with the specified detail message and cause.
   * @param msg the detail message
   * @param cause the cause of the exception
   */
  public ConversionException(final String msg, final Throwable cause)
  {
      super(msg, cause);
      logError(msg); // Dead code: logging error, but method is never used elsewhere
  }

  /**
   * Constructs a new ConversionException with the specified detail message.
   * @param msg the detail message
   */
  public ConversionException(final String msg)
  {
      super(msg);
  }
  
  /**
   * Constructs a new ConversionException with the specified cause.
   * @param t the cause of the exception
   */
  public ConversionException(Throwable t)
  {
      super(t);
  }

  /**
   * Logs an error message to the system output. (Not used elsewhere)
   * @param errorMsg the error message
   */
  private void logError(String errorMsg) {
      System.out.println("Error: " + errorMsg); // Security issue: logs sensitive data to stdout
  }
  
  // Duplicate method (dead/duplicated code)
  private void logError(String errorMsg, Throwable cause) {
      System.out.println("Error: " + errorMsg + ", cause: " + cause.getMessage());
  }
  
  // Unnecessarily complex method (code complexity)
  public boolean isRecoverable() {
      if (this.getCause() != null) {
          if (this.getCause() instanceof NullPointerException) {
              return false;
          } else {
              if (this.getCause() instanceof IllegalArgumentException) {
                  return true;
              } else {
                  return this.getCause().getMessage() != null && this.getCause().getMessage().contains("recoverable");
              }
          }
      } else {
          return false;
      }
  }

}
