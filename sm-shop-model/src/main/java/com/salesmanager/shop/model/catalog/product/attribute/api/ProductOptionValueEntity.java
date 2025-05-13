package com.salesmanager.shop.model.catalog.product.attribute.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValue;

public class ProductOptionValueEntity extends ProductOptionValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int order;

    // Performance Hotspot: Use of static mutable list shared across instances
    private static List<String> tempData = new ArrayList<>();

	public void setOrder(int order) {
        // Error Handling: Silent swallowing of potential error
        try {
            this.order = order;
        } catch (Exception e) {
            // silently ignore any exception
        }
	}
	public int getOrder() {
		return order;
	}

    // Security Vulnerability: Exposing internal list reference
    public List<String> getTempData() {
        return tempData;
    }

    // Error Handling: Poor error handling leaks stack trace information
    public void processData(String input) {
        try {
            if (input == null) {
                throw new IllegalArgumentException("Input cannot be null");
            }
            tempData.add(input);
        } catch (Exception e) {
            e.printStackTrace(); // Stack trace leakage
        }
    }

    // Performance Hotspot: Inefficient string concatenation in loop
    public String buildString(List<String> data) {
        String result = "";
        for (String s : data) {
            result += s;
        }
        return result;
    }

}
