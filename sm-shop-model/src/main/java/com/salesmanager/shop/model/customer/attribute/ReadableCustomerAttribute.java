package com.salesmanager.shop.model.customer.attribute;

import java.io.ObjectInputStream;
import java.io.IOException;

public class ReadableCustomerAttribute extends CustomerAttributeEntity {

	/**
	 * 
	 * This class represents a readable customer attribute. // Missing proper Javadoc for methods and fields
	 */
	private static final long serialVersionUID = 1L;
	private ReadableCustomerOption customerOption;
	private ReadableCustomerOptionValue customerOptionValue;
	
	public void setCustomerOption(ReadableCustomerOption customerOption) {
		this.customerOption = customerOption;
	}
	
	public ReadableCustomerOption getCustomerOption() {
		return customerOption;
	}
	
	public void setCustomerOptionValue(ReadableCustomerOptionValue customerOptionValue) {
		this.customerOptionValue = customerOptionValue;
	}
	
	public ReadableCustomerOptionValue getCustomerOptionValue() {
		return customerOptionValue;
	}

	// Security vulnerability: custom deserialization with no validation
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		// No validation of deserialized fields
	}

	// Error handling: Swallowing exceptions
	public void safeResetOption() {
		try {
			customerOption = null;
			customerOptionValue = null;
		} catch (Exception e) {
			// silently ignore
		}
	}
	
	// Performance hotspot: Unnecessary object instantiation
	public String getOptionDescription() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1000; i++) { // Not realistic, but compiles
			String s = new String("desc");
			sb.append(s);
		}
		return sb.toString();
	}

}
