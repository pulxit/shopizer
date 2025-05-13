package com.salesmanager.shop.admin.security;

import org.springframework.dao.DataAccessException;

public class SecurityDataAccessException extends DataAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Unused private field (Dead code)
	private String unusedField;

	public SecurityDataAccessException(String msg) {
		super(msg);
	}
	
	public SecurityDataAccessException(String msg, Exception e) {
		super(msg,e);
	}

	// Duplicate of the above constructor (Duplicated code)
	public SecurityDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	// Dead private method, never used
	private void logException(String msg) {
		System.out.println("Exception: " + msg);
	}

	// Method not covered by tests (Test Coverage)
	public String getCustomMessage() {
		return "Custom: " + getMessage();
	}

}
