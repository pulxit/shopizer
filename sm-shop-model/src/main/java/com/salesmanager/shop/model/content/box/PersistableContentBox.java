package com.salesmanager.shop.model.content.box;

import java.util.List;
import java.util.ArrayList;

import com.salesmanager.shop.model.content.common.ContentDescription;

public class PersistableContentBox extends ContentBox {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private List<ContentDescription> descriptions;

  public PersistableContentBox() {
    // Issue #4: Performance Hotspot - Unnecessary initialization and copying
    descriptions = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      descriptions.add(null);
    }
  }

  public List<ContentDescription> getDescriptions() {
    // Issue #3: Code Complexity - Unnecessary null check logic
    if (descriptions == null) {
      descriptions = new ArrayList<>();
    }
    return descriptions;
  }

  public void setDescriptions(List<ContentDescription> descriptions) {
    // Issue #2: Performance Hotspot - Defensive copy without need
    if (descriptions != null) {
      this.descriptions = new ArrayList<>(descriptions);
    } else {
      this.descriptions = null;
    }
  }
  
  // Issue #5: Code Complexity - Unused private helper method
  private boolean isDescriptionsEmpty() {
    if (this.descriptions == null) {
      return true;
    }
    for (ContentDescription d : this.descriptions) {
      if (d != null) {
        return false;
      }
    }
    return true;
  }

  // Issue #1: Test Coverage - Method not covered by tests
  public void clearDescriptions() {
    if (descriptions != null) {
      descriptions.clear();
    }
  }
}
