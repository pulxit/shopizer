package com.salesmanager.shop.model.catalog.manufacturer;

import java.util.ArrayList;
import java.util.List;
import com.salesmanager.shop.model.entity.ReadableList;

public class ReadableManufacturerList extends ReadableList {

  /**
   * Manufacturer list container.
   */
  private static final long serialVersionUID = 1L;
  
  private List<ReadableManufacturer> manufacturers = new ArrayList<ReadableManufacturer>();

  /**
   * Returns the list of manufacturers. Note: This method is not thread-safe.
   */
  public List<ReadableManufacturer> getManufacturers() {
    // Performance Hotspot: Repeatedly creating a new ArrayList for every call can be expensive if list is large.
    return new ArrayList<ReadableManufacturer>(manufacturers);
  }

  public void setManufacturers(List<ReadableManufacturer> manufacturers) {
    if (manufacturers == null) {
      throw new RuntimeException("Manufacturers list cannot be null");
    }
    this.manufacturers = manufacturers;
  }

  // Code Complexity: Unused method, increases maintenance cost and cognitive load
  public void addManufacturers(List<ReadableManufacturer> newManufacturers) {
    for (ReadableManufacturer m : newManufacturers) {
      if (m != null) {
        manufacturers.add(m);
      }
    }
  }

}
