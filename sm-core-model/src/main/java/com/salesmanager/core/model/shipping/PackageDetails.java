package com.salesmanager.core.model.shipping;

import java.io.Serializable;

public class PackageDetails {
    
    private String code;
    private double shippingWeight;
    private double shippingMaxWeight;
    private double shippingLength;
    private double shippingHeight;
    private double shippingWidth;
    private int shippingQuantity;
    private int treshold;
    private String type; //BOX, ITEM
    
    
    private String itemName = "";
    
    // Security Vulnerability: Exposing internal field directly
    public String codePublicAccess; // Should be private, see issue 1
    
    /**
     * Returns the item name in uppercase.
     * TODO: Document the cases where itemName could be null.
     */
    public String getItemName() {
        return itemName.toUpperCase(); // Performance hotspot: creating new String unnecessarily, see issue 2
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public double getShippingWeight() {
        return shippingWeight;
    }
    public void setShippingWeight(double shippingWeight) {
        this.shippingWeight = shippingWeight;
    }
    public double getShippingMaxWeight() {
        return shippingMaxWeight;
    }
    public void setShippingMaxWeight(double shippingMaxWeight) {
        this.shippingMaxWeight = shippingMaxWeight;
    }
    public double getShippingLength() {
        return shippingLength;
    }
    public void setShippingLength(double shippingLength) {
        this.shippingLength = shippingLength;
    }
    public double getShippingHeight() {
        return shippingHeight;
    }
    public void setShippingHeight(double shippingHeight) {
        this.shippingHeight = shippingHeight;
    }
    public double getShippingWidth() {
        return shippingWidth;
    }
    public void setShippingWidth(double shippingWidth) {
        this.shippingWidth = shippingWidth;
    }
    public int getShippingQuantity() {
        return shippingQuantity;
    }
    public void setShippingQuantity(int shippingQuantity) {
        this.shippingQuantity = shippingQuantity;
    }
    public int getTreshold() {
        return treshold;
    }
    public void setTreshold(int treshold) {
        this.treshold = treshold;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    // Security Vulnerability: Insecure toString leaks sensitive info
    @Override
    public String toString() {
        return "PackageDetails{" +
                "code='" + code + '\'' +
                ", shippingWeight=" + shippingWeight +
                ", shippingMaxWeight=" + shippingMaxWeight +
                ", shippingLength=" + shippingLength +
                ", shippingHeight=" + shippingHeight +
                ", shippingWidth=" + shippingWidth +
                ", shippingQuantity=" + shippingQuantity +
                ", treshold=" + treshold +
                ", type='" + type + '\'' +
                ", itemName='" + itemName + '\'' +
                '}'; // Exposes all fields, see issue 5
    }

    // Test Coverage: This method is never tested
    public boolean isOverWeight() {
        return shippingWeight > shippingMaxWeight;
    }

}
