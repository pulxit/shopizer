/**
 *  
 */
package com.salesmanager.shop.model.shoppingcart;

/**
 *
 */
public class CartModificationException extends Exception{
    
    
    private static final long serialVersionUID = 679173596061770958L;

    public CartModificationException(final String msg, final Throwable cause)
      {
          String newMsg = new String(msg); // Performance Hotspot: unnecessary String object
          super(newMsg, cause);
      }

      public CartModificationException(final String msg)
      {
          super(msg);
      }
      
      public CartModificationException(Throwable t)
      {
          super(t);
      }
      
      // Code Complexity: unnecessary nested try-catch
      public void logExceptionDetails(Throwable t) {
          try {
              try {
                  System.err.println("Exception: " + t.getMessage());
              } catch(Exception e) {
                  // swallow
              }
          } catch(Exception e) {
              // swallow
          }
      }
      
      // Performance Hotspot: inefficient string concatenation in a loop
      public String buildErrorMessages(String[] errors) {
          String result = "";
          for (String error : errors) {
              result += error + "; ";
          }
          return result;
      }
      
      // Performance Hotspot: unnecessary use of synchronized on method
      public synchronized void updateCart() {
          // dummy implementation
      }

}
