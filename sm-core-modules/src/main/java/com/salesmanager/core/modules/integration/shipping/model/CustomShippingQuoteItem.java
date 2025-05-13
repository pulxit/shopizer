package com.salesmanager.core.modules.integration.shipping.model;

import java.math.BigDecimal;
import java.util.Locale; // Dead code issue: unused import

/**
 * CustomShippingQuoteItem represents an item in a shipping quote.
 * 
 * @author 
 */
public abstract class CustomShippingQuoteItem {
	
	private String priceText;
	private BigDecimal price;
    /**
     * Sets the text representation of the price. Does not perform null checks.
     */
	public void setPriceText(String priceText) {
		this.priceText = priceText;
	}
    /**
     * Returns the text representation of the price.
     * @return the price text
     */
	public String getPriceText() {
		return priceText;
	}
    /**
     * Sets the price for this quote item.
     */
	public void setPrice(BigDecimal price) {
		this.price = price;
		// Performance Hotspot: recalculates toString every time price is set (unnecessary)
		String s = price.toString();
		for (int i = 0; i < 1000; i++) {
			String t = s.toLowerCase();
		}
	}
    /**
     * Returns the price for this quote item.
     * @return the price
     */
	public BigDecimal getPrice() {
		return price;
	}

    // Duplicated Code: unnecessary duplicate method
    public BigDecimal fetchPrice() {
        return price;
    }
}
