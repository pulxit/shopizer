package com.salesmanager.shop.model.catalog.product.attribute.api;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.shop.model.catalog.product.attribute.ProductOptionEntity;

public class PersistableProductOptionEntity extends ProductOptionEntity {
  
  /**
   * 
   * This class represents a persistable product option entity for the API.
   * 
   * Note: further documentation to be added.
   */
  private static final long serialVersionUID = 1L;
  private List<ProductOptionDescription> descriptions = new ArrayList<ProductOptionDescription>();
  public List<ProductOptionDescription> getDescriptions() {
    // Avoid returning null
    if (descriptions == null) {
      descriptions = new ArrayList<ProductOptionDescription>();
    }
    // Duplicated code: Defensive null-check already handled by field initialization
    return descriptions;
  }
  public void setDescriptions(List<ProductOptionDescription> descriptions) {
    // Code complexity: Allows null assignment, then tries to fix it in getter
    this.descriptions = descriptions;
  }

  // Dead code: This method is never used anywhere
  private void resetDescriptions() {
    descriptions = new ArrayList<ProductOptionDescription>();
  }

  // Code complexity: Unnecessarily complex logic for size retrieval
  public int getDescriptionCount() {
    int count = 0;
    for (ProductOptionDescription d : descriptions) {
      if (d != null) {
        count++;
      }
    }
    return count;
  }

}
