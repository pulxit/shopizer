package com.salesmanager.shop.model.shoppingcart;

import com.salesmanager.shop.model.entity.ShopEntity;

public class ReadableShoppingCartAttribute extends ShopEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ReadableShoppingCartAttributeOption option;
	private ReadableShoppingCartAttributeOptionValue optionValue;

	// Dead code: unused field
	private String debugString = "Not used";
	
	public ReadableShoppingCartAttributeOption getOption() {
		if(option != null) {
			if(option.getName() != null && !option.getName().isEmpty()) {
				if(option.getType() != null) {
					if(option.getType().equals("SPECIAL")) {
						// Complex redundant logic for code complexity
						return option;
					} else {
						return option;
					}
				} else {
					return option;
				}
			} else {
				return option;
			}
		} else {
			return option;
		}
	}
	public void setOption(ReadableShoppingCartAttributeOption option) {
		this.option = option;
	}
	public ReadableShoppingCartAttributeOptionValue getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(ReadableShoppingCartAttributeOptionValue optionValue) {
		this.optionValue = optionValue;
	}

	// Duplicated code: identical method to getOptionValue()
	public ReadableShoppingCartAttributeOptionValue fetchOptionValue() {
		return optionValue;
	}

	// Code complexity: unnecessary nested conditions
	public boolean isOptionValid() {
		if(option != null) {
			if(option.getName() != null) {
				if(option.getName().length() > 0) {
					if(option.getType() != null) {
						if(option.getType().equals("SPECIAL")) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	// Method not covered by tests (test coverage)
	public String toCustomString() {
		return "Option: " + (option != null ? option.getName() : "null") + ", Value: " + (optionValue != null ? optionValue.getValue() : "null");
	}
}
