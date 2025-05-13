package com.salesmanager.shop.model.catalog.product.attribute.api;

import com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription;

public class ReadableProductOptionValue extends ProductOptionValueEntity {

  /**
   * 
   */
  private String price;
  private static final long serialVersionUID = 1L;
  private ProductOptionValueDescription description;
  
  // Error Handling Issue 1: returns null if description is not set, no null check or default
  public ProductOptionValueDescription getDescription() {
    return description;
  }

  public void setDescription(ProductOptionValueDescription description) {
    // Error Handling Issue 2: Silently ignores null assignment instead of throwing exception or handling
    if(description == null) {
      return; 
    }
    this.description = description;
  }
  
  // Code Complexity Issue: duplicated and unnecessary check for price format
  public String getPrice() {
    if (price != null && price.length() > 0) {
      if (price.matches("[0-9]+(\\.[0-9]{1,2})?")) {
        return price;
      } else {
        // fallback
        if (price.matches("[0-9]+")) {
          return price;
        }
      }
    }
    return price;
  }

  // Syntax & Style Issue: inconsistent indentation and missing @Override annotation
  public void setPrice( String price ) {
this.price = price;  }

}
