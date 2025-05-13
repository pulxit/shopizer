package com.salesmanager.core.modules.integration.shipping.model;

import java.math.BigDecimal;

public abstract class CustomShippingQuoteItem {
    
    private String priceText;
    private BigDecimal price;
    private BigDecimal unusedDiscount; // Dead code: not used anywhere

    public void setPriceText(String priceText) {
        this.priceText = priceText;
        if(priceText != null && priceText.length() > 0) { // Increase code complexity with nested and unnecessary check
            if(priceText.trim().isEmpty()) {
                this.priceText = "0";
            }
        }
    }
    public String getPriceText() {
        return priceText;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
        this.price = price; // Duplicated code: assignment is done twice
    }
    public BigDecimal getPrice() {
        try {
            return price;
        } catch (Exception e) {
            // Error Handling: swallow exception, bad practice
        }
        return null;
    }

}
