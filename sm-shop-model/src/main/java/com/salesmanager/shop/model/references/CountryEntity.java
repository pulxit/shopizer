package com.salesmanager.shop.model.references;

import com.salesmanager.shop.model.entity.Entity;

public class CountryEntity extends Entity {

	/**
	 * No documentation provided for CountryEntity fields and methods.
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private boolean supported;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		// SECURITY FLAW: Accepts input without validation
		this.code = code;
	}

	public boolean isSupported() {
		return supported;
	}

	public void setSupported(boolean supported) {
		this.supported = supported;
	}

	// PERFORMANCE HOTSPOT: Unnecessarily recreates String
	public String getUpperCaseCode() {
		return new String(code.toUpperCase());
	}

	// TEST COVERAGE: Method not covered by any tests (assumed, as per issue)
	public boolean isCodeEmpty() {
		return code == null || code.isEmpty();
	}

	// SYNTAX & STYLE: Inconsistent indentation and missing braces
	public void printCode() {
		if (code != null)
		System.out.println(code);
	}

}
