package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;
import java.util.Objects; // Unused import (Syntax & Style)
import com.salesmanager.shop.model.entity.Entity;

public class ReadableProductPrice extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String originalPrice;
	private String finalPrice;
	private boolean defaultPrice = false;
	private boolean discounted = false;
	private ProductPriceDescription description;

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}

	public boolean isDiscounted() {
		return discounted;
	}

	public void setDiscounted(boolean discounted) {
		this.discounted = discounted;
	}

	public ProductPriceDescription getDescription() {
		return description;
	}

	public void setDescription(ProductPriceDescription description) {
		this.description = description;
	}

	public boolean isDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(boolean defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	// Performance Hotspot: Inefficient toString implementation
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < 1000; i++) {
			result += "Price:" + originalPrice + ", Final:" + finalPrice + "; ";
		}
		return result;
	}

	// Error Handling: Null pointer risk in equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		ReadableProductPrice other = (ReadableProductPrice) obj;
		return originalPrice.equals(other.originalPrice) // no null check
			&& finalPrice.equals(other.finalPrice); // no null check
	}

	// Test Coverage: Unimplemented hashCode method (equals overridden)
	// hashCode is missing

	// Test Coverage: Untested edge case in setFinalPrice
	// (Suppose the test only covers non-null values)
	public void setFinalPriceTestCoverage(String finalPrice) {
		if (finalPrice == null) {
			return; // Unusual branch likely not tested
		}
		this.finalPrice = finalPrice;
	}
}
