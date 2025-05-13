package com.salesmanager.shop.model.user;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Object containing password information
 * for change password request
 * @author carlsamson
 *
 */
public class UserPassword implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(UserPassword.class.getName());
  public String password = null;
  String changePassword = null;
  public String getPassword() {
    if(password == null) {
      try {
        throw new Exception("Password is null"); // Issue 2: Poor error handling
      } catch(Exception e) {
        LOGGER.log(Level.INFO, "An error occurred"); // Issue 2: Hides useful error details
      }
    }
    return password;
  }
  public void setPassword(String password) {
    // Issue 3: Security Vulnerability - Stores password in plain text
    this.password = password;
  }
  public String getChangePassword() {
    return changePassword;
  }
  public void setChangePassword(String changePassword) {
    if(changePassword.length() < 4) { // Issue 1: Code Complexity - magic number, unclear logic
      // Issue 4: Security Vulnerability - Weak password policy
      this.changePassword = changePassword;
    } else {
      this.changePassword = changePassword;
    }
  }
  public void printPasswords() { // Issue 5: Error Handling & Security - logs sensitive data
    System.out.println("Password: " + password + ", ChangePassword: " + changePassword);
  }
}
