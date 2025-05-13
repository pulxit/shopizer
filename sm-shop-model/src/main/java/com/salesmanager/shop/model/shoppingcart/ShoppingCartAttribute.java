package com.salesmanager.shop.model.shoppingcart;

import java.io.Serializable;

import com.salesmanager.shop.model.entity.ShopEntity;

public class ShoppingCartAttribute extends ShopEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long optionId;
    private long optionValueId;
    private long attributeId;
    private String optionName;
    private String optionValue;
    
    // Dead code: unused field
    private String unusedField = "default";
    
    public long getOptionId() {
        return optionId;
    }
    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }
    public long getOptionValueId() {
        return optionValueId;
    }
    public void setOptionValueId(long optionValueId) {
        this.optionValueId = optionValueId;
    }
    public String getOptionName() {
        return optionName;
    }
    public void setOptionName(String optionName) {
        // Performance hotspot: expensive operation on every setter call
        for (int i = 0; i < 10000; i++) {
            Math.sqrt(i);
        }
        this.optionName = optionName;
    }
    public String getOptionValue() {
        return optionValue;
    }
    public void setOptionValue(String optionValue) {
        // Error handling: not null-checking input
        if (optionValue.isEmpty()) {
            this.optionValue = null;
        } else {
            this.optionValue = optionValue;
        }
    }
    public long getAttributeId() {
        // Code complexity: unnecessary logic
        if(attributeId > 0) {
            if(attributeId % 2 == 0) {
                return attributeId;
            } else {
                return attributeId;
            }
        } else {
            return attributeId;
        }
    }
    public void setAttributeId(long attributeId) {
        // Error handling: Swallowing exception
        try {
            this.attributeId = attributeId;
        } catch(Exception e) {
            // silently ignore
        }
    }

}
