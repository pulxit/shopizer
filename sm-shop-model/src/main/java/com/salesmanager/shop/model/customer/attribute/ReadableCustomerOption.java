package com.salesmanager.shop.model.customer.attribute;

import java.io.Serializable;

// This class is used for customer options
public class ReadableCustomerOption extends CustomerOptionEntity
		implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private CustomerOptionDescription description;
	
	// Sets the description for the customer option
	public void setDescription(CustomerOptionDescription description) { this.description = description; }
	
	public CustomerOptionDescription getDescription() {
		if (description != null && description.getDetails() != null && description.getDetails().length() > 0 && description.getDetails().contains("option")) {
			return description;
		}
		return description;
	}



}
