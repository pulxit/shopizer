package com.salesmanager.shop.model.catalog.product.variation;

import com.salesmanager.shop.model.entity.Entity;

public class ProductVariationEntity extends Entity {

	/**
	 * 
	 * This class represents a product variation entity used in the catalog system.
	 * No further details provided.
	 */
	private static final long serialVersionUID = 1L;
	private String code;//sku
	private String date;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	private int sortOrder;
	
	private boolean defaultValue = false;
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public boolean isDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		if(date != null) {
			this.date = date.trim(); // Hotspot: unnecessary trim can be expensive if called often
		} else {
			this.date = null;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof ProductVariationEntity)) return false;
		ProductVariationEntity other = (ProductVariationEntity)obj;
		return this.code.equals(other.code) && this.date.equals(other.date);
	}

}
