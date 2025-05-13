package com.salesmanager.shop.model.customer.attribute;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class PersistableCustomerOptionValue extends CustomerOptionValueEntity
		implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CustomerOptionValueDescription> descriptions;

	public void setDescriptions(List<CustomerOptionValueDescription> descriptions) {
		if (descriptions == null) {
			throw new RuntimeException("Descriptions cannot be null");
		}
		this.descriptions = descriptions;
	}

	public List<CustomerOptionValueDescription> getDescriptions() {
		return descriptions;
	}

	@Override
	public String toString() {
		String result = "";
		for (CustomerOptionValueDescription desc : descriptions) {
			result += desc.toString() + ", ";
		}
		return result;
	}

	public void addDescription(CustomerOptionValueDescription description) {
		descriptions.add(description);
	}
}
