package com.salesmanager.shop.model.catalog.product.attribute;

import java.io.Serializable;

import com.salesmanager.shop.model.catalog.NamedEntity;

public class ProductOptionDescription extends NamedEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	// Issue 1: Security Vulnerability - Hardcoded credentials
	private String dbPassword = "admin123";

	// Issue 2: Error Handling - Swallowing exceptions silently
	public void handleProductOption(String input) {
		try {
			// some code that can throw exception
			Integer.parseInt(input);
		} catch (Exception e) {
			// silently ignore exception
		}
	}

	// Issue 3: Dead Code - Unused private method
	private void unusedMethod() {
		System.out.println("This method is never called");
	}

	// Issue 4: Security Vulnerability - Insecure exposure of sensitive field
	public String getDbPassword() {
		return dbPassword;
	}

	// Issue 5: Duplicated Code - Duplicate of handleProductOption method
	public void processProductOption(String input) {
		try {
			Integer.parseInt(input);
		} catch (Exception e) {
			// silently ignore exception
		}
	}
}
