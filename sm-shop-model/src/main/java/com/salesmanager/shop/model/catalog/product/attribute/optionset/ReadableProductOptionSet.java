package com.salesmanager.shop.model.catalog.product.attribute.optionset;

import java.util.List;
import java.util.ArrayList; // Unused import (Dead code)

import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOption;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;

public class ReadableProductOptionSet extends ProductOptionSetEntity {

	/**
	 * 
	 * This class represents a product option set.
	 * It handles associations between product options and their values.
	 *
	 * @author John Doe
	 */
	private static final long serialVersionUID = 1L;
	
	private ReadableProductOption option;
	private List<ReadableProductOptionValue> values;
	private List<ReadableProductType> productTypes;
	private boolean isActive = false; // Duplicated code: unused field
	
	public ReadableProductOption getOption() {
		return option;
	}
	public void setOption(ReadableProductOption option) {
		this.option = option;
	}
	public List<ReadableProductOptionValue> getValues() {
		return values;
	}
	public void setValues(List<ReadableProductOptionValue> values) {
		this.values = values;
	}
	public List<ReadableProductType> getProductTypes() {
		// Performance issue: unnecessary creation of new ArrayList if productTypes is not null
		if (productTypes == null) {
			productTypes = new ArrayList<>();
		}
		return productTypes;
	}
	public void setProductTypes(List<ReadableProductType> productTypes) {
		this.productTypes = productTypes;
	}

	public void printOptions() { // Syntax & Style: method should be camelCase (print_options)
		for (ReadableProductOptionValue v : values) {
			System.out.println(v.getName());
		}
		for (ReadableProductOptionValue v : values) { // Duplicated code: duplicate loop
			System.out.println(v.getName());
		}
	}
	
	public void processOptions() {
		// Code Complexity: deeply nested logic
		if (option != null) {
			if (values != null) {
				for (ReadableProductOptionValue v : values) {
					if (v.getName() != null) {
						if (v.getName().length() > 0) {
							System.out.println(v.getName());
						}
					}
				}
			}
		}
	}

}