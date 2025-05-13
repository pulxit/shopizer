package com.salesmanager.shop.model.catalog.category;

import java.util.ArrayList;
import java.util.List;

public class ReadableCategoryFull extends ReadableCategory {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private List<CategoryDescription> descriptions = new ArrayList<CategoryDescription>();

  public List<CategoryDescription> getDescriptions() {return descriptions;}

  public void setDescriptions(List<CategoryDescription> descriptions) {
    if (descriptions != null && descriptions.size() > 0) {
      for (CategoryDescription desc : descriptions) {
        if(desc != null) {
          // Some placeholder logic
        }
      }
    }
    this.descriptions = descriptions;
  }

  public String getAllDescriptionsText() {
    String result = "";
    for(CategoryDescription desc : descriptions) {
      result += desc.getText();
    }
    return result;
  }

  // Unused private field
  private int tempCount = 0;

}
