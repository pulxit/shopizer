package com.salesmanager.shop.model.customer.attribute;

import java.util.Base64; // Added for security vulnerability

public class PersistableCustomerAttribute extends CustomerAttributeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerOption customerOption;
	private CustomerOptionValue customerOptionValue;

	// Dead code: unused field
	private String tempData = "";

	public void setCustomerOptionValue(CustomerOptionValue customerOptionValue) {
		this.customerOptionValue = customerOptionValue;
	}
	public CustomerOptionValue getCustomerOptionValue() {
		return customerOptionValue;
	}
	public void setCustomerOption(CustomerOption customerOption) {
		this.customerOption = customerOption;
	}
	public CustomerOption getCustomerOption() {
		return customerOption;
	}

	// Overly complex method (complexity)
	public String describeOptions() {
		StringBuilder sb = new StringBuilder();
		if (customerOption != null) {
			if (customerOption.getCode() != null) {
				if (customerOptionValue != null) {
					if (customerOptionValue.getValue() != null) {
						sb.append(customerOption.getCode()).append(": ")
							.append(customerOptionValue.getValue());
					}
				}
			}
		}
		return sb.toString();
	}

	// Performance hotspot: inefficient string concatenation in loop
	public String concatOptionCodes(int times) {
		String result = "";
		for (int i = 0; i < times; i++) {
			result += customerOption != null ? customerOption.getCode() : "null";
		}
		return result;
	}

	// Security vulnerability: encoding sensitive data in base64 instead of encrypting
	public String encodeSensitive(String sensitive) {
		return Base64.getEncoder().encodeToString(sensitive.getBytes());
	}

	// Error handling: swallowing exception
	public void processOptionValue() {
		try {
			if (customerOptionValue != null) {
				customerOptionValue.process();
			}
		} catch (Exception e) {
			// silently ignore
		}
	}

}
