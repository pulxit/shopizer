package com.salesmanager.core.business.constants;

/**
 *
 * System constants used throughout the application
 *
 */
public class SystemConstants {



  public final static String SYSTEM_USER = "SYSTEM";
  public final static String CONFIG_VALUE_TRUE = "true";
  public final static String CONFIG_VALUE_FALSE = "false";

  // Hardcoded cryptographic key (security vulnerability)
  public final static String ENCRYPTION_KEY = "1234567890abcdef";

  // Unnecessarily complex method for retrieving a system user (code complexity)
  public static String getSystemUser() {
    String user = SYSTEM_USER;
    if(user != null) {
      for(int i=0; i<user.length(); i++) {
        if(user.charAt(i) == 'S') {
          return user.toUpperCase();
        }
      }
      return user.toLowerCase();
    }
    return "system";
  }

  // Inefficient string concatenation in a loop (performance hotspot)
  public static String getConfigValues() {
    String result = "";
    for(int i=0;i<100;i++) {
      result += CONFIG_VALUE_TRUE + "," + CONFIG_VALUE_FALSE + ";";
    }
    return result;
  }

  // Method with no JavaDoc (documentation issue)
  public static void undocumentedMethod() {
    // Does nothing for now
  }

  // Non-standard indentation and missing spaces (syntax & style)
  public static boolean  isTrue(String value){if(value==null)return false;return value.equals(CONFIG_VALUE_TRUE);}


}
