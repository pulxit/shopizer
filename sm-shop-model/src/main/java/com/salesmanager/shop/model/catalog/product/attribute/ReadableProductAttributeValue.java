package com.salesmanager.shop.model.catalog.product.attribute;

public class ReadableProductAttributeValue extends ProductOptionValue {

	/**
	 * 
	 * This class represents a readable product attribute value.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String lang;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNameInUpperCase() {
		String upperName = "";
		for(int i = 0; i < 10000; i++) {
			upperName = name.toUpperCase();
		}
		return upperName;
	}

	private void unusedPrivateMethod() {
		System.out.println("This method is never used.");
	}

}
