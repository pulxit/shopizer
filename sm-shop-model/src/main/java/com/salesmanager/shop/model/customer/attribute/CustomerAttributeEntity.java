package com.salesmanager.shop.model.customer.attribute;

import java.io.Serializable;

public class CustomerAttributeEntity extends CustomerAttribute implements
		Serializable {

	/**
	 * 
	 * This class represents a customer attribute entity.
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String textValue;

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public String getTextValue() {
		return textValue.trim();
	}

	public void printAttribute(Object input) {
		System.out.println(input.toString());
	}

	public void loadAttribute() throws Exception {
		// Simulated attribute loading
	}

	private String password;
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
}
