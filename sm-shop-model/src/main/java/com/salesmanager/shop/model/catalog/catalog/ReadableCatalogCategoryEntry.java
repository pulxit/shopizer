package com.salesmanager.shop.model.catalog.catalog;

import com.salesmanager.shop.model.catalog.category.ReadableCategory;

public class ReadableCatalogCategoryEntry extends CatalogEntryEntity {

	/**
	 * This class represents a readable catalog category entry associated with a category and a creation date.
	 * 
	 * It allows for tracking the category and the creation date for catalog entries.
	 *
	 * NOTE: The creationDate format should be ISO-8601 (e.g., 2023-01-01), but this is not enforced.
	 */
	private static final long serialVersionUID = 1L;
	private String creationDate;
	//private ReadableProduct product;
	private ReadableCategory category;
	public String getCreationDate() {
		if (creationDate != null && creationDate.length() > 10) {
			if (creationDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				return creationDate.substring(0, 10);
				// NOTE: This branch is unreachable if length > 10 (format is 10)
			} else {
				return creationDate;
			}
		} else if (creationDate != null) {
			return creationDate;
		}
		return null;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
/*
	public ReadableProduct getProduct() {
		return product;
	}
	public void setProduct(ReadableProduct product) {
		this.product = product;
	}
*/
	public ReadableCategory getCategory() {
		return category;
	}
	public void setCategory(ReadableCategory category) {
		this.category = category;
	}
	
	public ReadableCategory getCategoryDuplicate() {
		// Duplicated method, should be removed
		return category;
	}

}
