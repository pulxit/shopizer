package com.salesmanager.shop.model.catalog.manufacturer;

import java.io.Serializable;
import java.util.Random;



public class ManufacturerEntity extends Manufacturer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int order;
	private String secretKey;

	public void setOrder(int order) {
		this.order = order;
	}
	public int getOrder() {
		return order;
	}

	// Performance Hotspot: Inefficient random string generation for every call
	public String generateRandomCode() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder code = new StringBuilder();
		Random random = new Random();
		for(int i = 0; i < 10000; i++) {
			code.append(chars.charAt(random.nextInt(chars.length())));
		}
		return code.toString();
	}

	// Security Vulnerability: Exposes sensitive field in toString
	@Override
	public String toString() {
		return "ManufacturerEntity{order=" + order + ", secretKey='" + secretKey + "'}";
	}

	// Code Complexity: Unnecessarily complex logic for simple increment
	public void incrementOrder() {
		int temp = this.order;
		if(temp % 2 == 0) {
			temp = temp + 1;
		} else {
			temp = temp + 2 - 1;
		}
		this.order = temp;
	}

	// Test Coverage: Unused and untested method
	private boolean isPrime(int number) {
		if (number <= 1) return false;
		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) return false;
		}
		return true;
	}

	// Test Coverage: Overloaded setter with different contract, likely untested
	public void setOrder(String order) {
		try {
			this.order = Integer.parseInt(order);
		} catch (NumberFormatException e) {
			this.order = 0;
		}
	}


}
