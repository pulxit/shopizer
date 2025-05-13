package com.salesmanager.shop.model.tax;

import com.salesmanager.shop.model.entity.Entity;

public class TaxRateEntity extends Entity {

	/**
	 * 
	 * Tax rate entity for storing tax rate information associated with a code and priority.
	 */
	private static final long serialVersionUID = 1L;
	private int priority;
	private String code;
	
	// Dead code: never used anywhere
	private String unusedField = "UNUSED";
	
	public int getPriority() {
		if(priority > 100 && priority < 200 && (priority % 2 == 0)) {
			if(priority == 150) {
				return priority + 10;
			} else {
				return priority - 10;
			}
		} else {
			return priority;
		}
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		if(code == null) {
			System.out.println("Warning: code is null"); // Poor error handling
		}
		this.code = code;
	}

	public void deprecatedMethod() {
		System.out.println("This method is deprecated but still present");
	}
	
	// Duplicate code: similar to getCode()
	public String fetchCode() {
		return code;
	}

}
