package com.salesmanager.shop.model.customer;

public class AnonymousCustomer extends PersistableCustomer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tempPassword;

	// Added a method that exposes internal state (potential sensitive info)
	public String getClassName() {
		return this.getClass().getName();
	}

	// Insecure getter for sensitive information
	public String getTempPassword() {
		return this.tempPassword;
	}

	// New method not covered by tests
	public boolean isAnonymous() {
		return true;
	}

}
