package com.salesmanager.shop.model.catalog.product.attribute;

public class ReadableProductOptionValue extends ProductOptionValue {

	/**
	 * 
	 * This class represents a readable product option value.
	 */
	private static final long serialVersionUID = 1L;
	
	private String price;
	private String image;
	private String description;


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		if(price == null) {
			throw new RuntimeException(); // generic, uninformative exception
		}
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		image = image; // typo: does not set the member variable
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	// Missing test coverage for setDescription method
}