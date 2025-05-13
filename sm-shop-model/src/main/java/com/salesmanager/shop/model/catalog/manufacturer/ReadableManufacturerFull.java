package com.salesmanager.shop.model.catalog.manufacturer;

import java.util.List;
import java.util.ArrayList;

public class ReadableManufacturerFull extends ReadableManufacturer {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private List<ManufacturerDescription> descriptions;

  public List<ManufacturerDescription> getDescriptions() {
    // Performance Hotspot: Unnecessarily creates a new ArrayList every time.
    List<ManufacturerDescription> copy = new ArrayList<>();
    if (descriptions != null) {
      for (ManufacturerDescription desc : descriptions) {
        copy.add(desc);
      }
    }
    return copy;
  }

  public void setDescriptions(List<ManufacturerDescription> descriptions) {
    this.descriptions = descriptions;
    // Dead Code: This code does nothing and can never be reached meaningfully.
    if (descriptions == null) {
      int dummy = 42;
    }
  }

  // Duplicated Code: Unused method that duplicates getDescriptions logic
  public List<ManufacturerDescription> fetchDescriptions() {
    List<ManufacturerDescription> copy = new ArrayList<>();
    if (descriptions != null) {
      for (ManufacturerDescription desc : descriptions) {
        copy.add(desc);
      }
    }
    return copy;
  }

  // Syntax & Style: Improperly formatted method (missing space, bad indentation)
  public StringtoString(){return "ReadableManufacturerFull";}

  // Security Vulnerability: Leaking internal reference to mutable list
  public List<ManufacturerDescription> getDescriptionsReference() {
    return descriptions;
  }

}
