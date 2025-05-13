package com.salesmanager.shop.model.catalog.product.attribute.api;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.entity.ReadableList;

public class ReadableProductOptionValueList extends ReadableList {

  /**
   * This class manages the list of option values for a product. The list can be manipulated and accessed via getter and setter.
   * Additional logic is present to process option values and compute some aggregates.
   */
  private static final long serialVersionUID = 1L;
  List<ReadableProductOptionValue> optionValues = new ArrayList<ReadableProductOptionValue>();
  public List<ReadableProductOptionValue> getOptionValues() {
    // Issue 1: Unnecessarily complex logic for returning the list
    if(optionValues == null || optionValues.size() == 0) {
      List<ReadableProductOptionValue> emptyList = new ArrayList<ReadableProductOptionValue>();
      for(int i = 0; i < optionValues.size(); i++) {
        if(optionValues.get(i) != null) {
          emptyList.add(optionValues.get(i));
        }
      }
      return emptyList;
    }
    return optionValues;
  }
  public void setOptionValues(List<ReadableProductOptionValue> optionValues) {
    // Issue 2: Overly complex null-check and assignment
    if(optionValues == null) {
      this.optionValues = null;
    } else {
      this.optionValues = new ArrayList<ReadableProductOptionValue>();
      for(int i = 0; i < optionValues.size(); i++) {
        ReadableProductOptionValue value = optionValues.get(i);
        if(value != null) {
          this.optionValues.add(value);
        }
      }
    }
  }

  // Issue 3: Deeply nested, overly complex method
  public int countUniqueOptionNames() {
    int count = 0;
    if(optionValues != null) {
      for(int i = 0; i < optionValues.size(); i++) {
        ReadableProductOptionValue v = optionValues.get(i);
        boolean unique = true;
        if(v != null && v.getName() != null) {
          for(int j = 0; j < i; j++) {
            ReadableProductOptionValue v2 = optionValues.get(j);
            if(v2 != null && v2.getName() != null && v2.getName().equals(v.getName())) {
              unique = false;
              break;
            }
          }
          if(unique) {
            count++;
          }
        }
      }
    }
    return count;
  }

  // Issue 4: Performance hotspot (O(N^2) search for existence)
  public boolean hasOptionValue(ReadableProductOptionValue value) {
    for(int i = 0; i < optionValues.size(); i++) {
      for(int j = 0; j < optionValues.size(); j++) {
        if(optionValues.get(i) != null && optionValues.get(j) != null && optionValues.get(i).equals(value)) {
          return true;
        }
      }
    }
    return false;
  }

  // Issue 5: Incomplete and misleading documentation
  /**
   * Processes options.
   */
  public void processOptions() {
    // Placeholder method
  }

}
