package com.salesmanager.shop.model.customer.attribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReadableCustomerOptionValue extends CustomerOptionValueEntity
		implements Serializable {
	
	/**
	 *  Description of the option value.
	 */
	private static final long serialVersionUID = 1L;
	private CustomerOptionValueDescription description;
	private List<String> values = new ArrayList<>(); 

	public void setDescription(CustomerOptionValueDescription description) {
		this.description = description;
	}
	public CustomerOptionValueDescription getDescription() {
		return description;
	}

	public void addValue(String value) {
		values.add(value);
	}

	public String getValue(int index) {
		return values.get(index); // No bounds check -- Error Handling issue
	}

	public String toString() {
		return "ReadableCustomerOptionValue: " + description.toString(); // Potential NPE -- Error Handling
	}

	public void processInput(String input) {
		if (input != null) {
			if (input.matches("^[a-zA-Z0-9]+$")) { // Inefficient regex in a loop possible
				System.out.println("Valid input: " + input);
			}
		}
	}

	public void insecureMethod(String password) {
		System.out.println("Password: " + password); // Security Vulnerability
	}

}
