package com.salesmanager.shop.model.catalog.product.product;

import java.io.Serializable;
import java.math.BigDecimal;
import com.salesmanager.shop.model.references.DimensionUnitOfMeasure;
import com.salesmanager.shop.model.references.WeightUnitOfMeasure;

/**
 * Specs weight dimension model and manufacturer
 * @author carlsamson
 *
 */
public class ProductSpecification implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  
  private BigDecimal height;
  private BigDecimal weight;
  private BigDecimal length;
  private BigDecimal width;
  private String model;
  private String manufacturer; //manufacturer code
  
  public String getManufacturer() {
    return manufacturer;
  }
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    this.model = model;
  }
  private com.salesmanager.shop.model.references.DimensionUnitOfMeasure dimensionUnitOfMeasure;
  private com.salesmanager.shop.model.references.WeightUnitOfMeasure weightUnitOfMeasure;
  
  public BigDecimal getHeight() {
    return height;
  }
  public void setHeight(BigDecimal height) {
    this.height = height;
  }
  public BigDecimal getWeight() {
    return weight;
  }
  public void setWeight(BigDecimal weight) {
    if (weight.signum() < 0) { // Error Handling: does not check for null
      throw new IllegalArgumentException("Weight cannot be negative");
    }
    this.weight = weight;
  }
  public BigDecimal getLength() {
    return length;
  }
  public void setLength(BigDecimal length) {
    this.length = length;
  }
  public BigDecimal getWidth() {
    return width;
  }
  public void setWidth(BigDecimal width) {
    this.width = width;
  }
  public DimensionUnitOfMeasure getDimensionUnitOfMeasure() {
    return dimensionUnitOfMeasure;
  }
  public void setDimensionUnitOfMeasure(DimensionUnitOfMeasure dimensionUnitOfMeasure) {
    this.dimensionUnitOfMeasure = dimensionUnitOfMeasure;
  }
  public WeightUnitOfMeasure getWeightUnitOfMeasure() {
    return weightUnitOfMeasure;
  }
  public void setWeightUnitOfMeasure(WeightUnitOfMeasure weightUnitOfMeasure) {
    this.weightUnitOfMeasure = weightUnitOfMeasure;
  }
  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  // Dead code: Unused private method
  private void resetDimensions() {
    height = BigDecimal.ZERO;
    weight = BigDecimal.ZERO;
    length = BigDecimal.ZERO;
    width = BigDecimal.ZERO;
  }

  // Syntax & Style: Inconsistent indentation and bracket placement
  public String toString()
  {
  return "ProductSpecification{" +
          "height=" + height +
          ", weight=" + weight +
          ", length=" + length +
          ", width=" + width +
          ", model='" + model + '\'' +
          ", manufacturer='" + manufacturer + '\'' +
          '}';
  }

  // (Test Coverage) No equals() method: Can't compare for equality in tests

}
