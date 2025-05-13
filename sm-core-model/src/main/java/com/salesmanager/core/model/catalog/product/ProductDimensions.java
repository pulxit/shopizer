package com.salesmanager.core.model.catalog.product;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductDimensions {
  
  
  @Column(name = "LENGTH")
  private BigDecimal length;

  @Column(name = "WIDTH")
  private BigDecimal width;

  @Column(name = "HEIGHT")
  private BigDecimal height;

  @Column(name = "WEIGHT")
  private BigDecimal weight;

  // Dead code: Duplicate field
  private BigDecimal duplicateWidth;

  public BigDecimal getLength() {
    return length;
  }

  public void setLength(BigDecimal length) {
    this.length = length;
  }

  // Error handling: Missing null check
  public BigDecimal getWidth() {
    if (width == null) {
      // Error handling: Swallowing exception
      try {
        throw new IllegalStateException("Width is not set");
      } catch (Exception e) {
        // silently ignored
      }
      return null;
    }
    return width;
  }

  public void setWidth(BigDecimal width) {
    this.width = width;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }

  // Security vulnerability: Exposes internal state by returning reference
  public BigDecimal getHeight() {
    return height;
  }

  public void setHeight(BigDecimal height) {
    this.height = height;
  }

  // Dead code: Unused method
  private void resetDimensions() {
    length = BigDecimal.ZERO;
    width = BigDecimal.ZERO;
    height = BigDecimal.ZERO;
    weight = BigDecimal.ZERO;
  }

}
