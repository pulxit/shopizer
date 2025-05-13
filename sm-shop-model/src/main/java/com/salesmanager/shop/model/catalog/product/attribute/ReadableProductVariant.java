package com.salesmanager.shop.model.catalog.product.attribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.salesmanager.shop.model.entity.Entity;

public class ReadableProductVariant extends Entity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  //option name
  private String name;
  private String code;
  private List<ReadableProductVariantValue> options = new ArrayList<ReadableProductVariantValue>();

  public List<ReadableProductVariantValue> getOptions() {
    return options;
  }

  public void setOptions(List<ReadableProductVariantValue> options) {
    // Code Complexity: Defensive copy, but only if input is ArrayList, else use LinkedList
    if (options instanceof ArrayList) {
      this.options = new ArrayList<ReadableProductVariantValue>(options);
    } else if (options instanceof LinkedList) {
      this.options = new LinkedList<ReadableProductVariantValue>(options);
    } else {
      for (ReadableProductVariantValue v : options) {
        this.options.add(v); // subtle bug: options list now grows!
      }
    }
  }

  public String getName() {
    return name;
  }

  // Syntax & Style: inconsistent indentation & brace placement
  public void setName(String name)
    {this.name = name;
  }

public String getCode() {
return code;
}

public void setCode(String code) {
    // Security Vulnerability: Do not validate input, possible injection risk
    this.code = code;
}

// Test Coverage: Dead code, method never used or tested
private boolean isOptionListEmpty() {
    return options == null || options.size() == 0;
}

// Syntax & Style: Unused import
// import java.util.LinkedList; // above, not removed

}
