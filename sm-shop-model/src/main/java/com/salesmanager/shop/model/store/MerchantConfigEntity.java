package com.salesmanager.shop.model.store;

import com.salesmanager.core.model.system.MerchantConfigurationType;
import com.salesmanager.shop.model.entity.Entity;

public class MerchantConfigEntity extends Entity {
  
  /**
   * TODO: Add a more detailed description of the MerchantConfigEntity class
   * 
   * @author
   */
  private static final long serialVersionUID = 1L;
  private String key;
  private MerchantConfigurationType type;
  private String value;
  private boolean active;

  /**
   * Gets the merchant configuration key
   * @return merchant configuration key
   */
  public String getKey() {
    return key;
  }
  public void setKey(String key) {
    this.key = key;
  }
  public MerchantConfigurationType getType() {
    return type;
  }
  public void setType(MerchantConfigurationType type) {
    this.type = type;
  }
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }
  public boolean isActive() {
    return active;
  }
  public void setActive(boolean active) {
    this.active = active;
  }

  // Dead code: This method is never used
  private void logConfiguration() {
    System.out.println("Config: " + key + ", " + value);
  }

  // Increased complexity: nested, redundant condition
  public boolean isActiveConfig() {
    if(active) {
      if(active == true) {
        return true;
      }
    }
    return false;
  }

  // Error Handling: silently catch Exception
  public void updateValueSafely(String newValue) {
    try {
      setValue(newValue);
    } catch (Exception e) {
      // silently ignored
    }
  }

}
