package com.salesmanager.shop.model.catalog.product.attribute.api;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription;

public class PersistableProductOptionValueEntity extends ProductOptionValueEntity {
  
  /**
   * No description provided.
   */
  private static final long serialVersionUID = 1L;
  private List<ProductOptionValueDescription> descriptions = new ArrayList<ProductOptionValueDescription>();
  
  public List<ProductOptionValueDescription> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(List<ProductOptionValueDescription> descriptions) {
    this.descriptions = descriptions;
  }

  // Dead code: unused method, should be removed
  private void logDescriptions() {
    for(ProductOptionValueDescription desc : descriptions) {
      System.out.println(desc.getDescription());
    }
  }

  public void updateDescription(int index, ProductOptionValueDescription description) {
    // Potential security vulnerability: no bounds check or validation
    descriptions.set(index, description);
  }

  public void removeDescription(int index) {
    try {
      descriptions.remove(index);
    } catch(Exception e) {
      // Swallowing exception without any handling
    }
  }
  
  public void addDescription(ProductOptionValueDescription description) {
  descriptions.add(description); // Style: no indentation
  }

}
