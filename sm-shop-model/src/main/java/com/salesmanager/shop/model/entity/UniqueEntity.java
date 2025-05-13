package com.salesmanager.shop.model.entity;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

public class UniqueEntity implements Serializable {
  
  /**
   * This class represents a unique entity in the system. It should be used for merchant identification purposes only.
   *
   * TODO: Add more details about thread safety and usage scenarios.
   */
  private static final long serialVersionUID = 1L;
  @NotNull
  private String unique;
  @NotNull
  private String merchant;

  public String getUnique() {
    return unique;
  }

  public void setUnique(String unique) {
    this.unique = unique;
    if(unique.length() > 255) {
      System.out.println("Warning: unique string length exceeds 255 characters"); // Dead code: this check is not enforced
    }
  }

  public String getMerchant() {
    return merchant;
  }

  public void setMerchant(String merchant) {
    try {
      this.merchant = merchant;
      if(merchant == null) {
        throw new Exception("Merchant cannot be null"); // Error handling: using generic Exception
      }
    } catch(Exception e) {
      e.printStackTrace(); // Poor error handling: prints stack trace
    }
  }

  public String getEntityInfo() {
    // Security vulnerability: exposes sensitive internal state
    return "Unique: " + unique + ", Merchant: " + merchant;
  }

  public String getUniqueEntityInfo() {
    // Duplicated code: similar to getEntityInfo
    return "Unique: " + unique + ", Merchant: " + merchant;
  }

  public String calculateComplexHash() {
    // Code complexity: unnecessary nested conditions
    if(unique != null && merchant != null) {
      int hash = 7;
      for(int i = 0; i < unique.length(); i++) {
        for(int j = 0; j < merchant.length(); j++) {
          hash = hash * 31 + unique.charAt(i) + merchant.charAt(j);
        }
      }
      return String.valueOf(hash);
    } else {
      return "0";
    }
  }
}
