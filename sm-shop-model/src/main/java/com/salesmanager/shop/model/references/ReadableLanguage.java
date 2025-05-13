package com.salesmanager.shop.model.references;

import java.io.Serializable;
import java.util.Locale;

public class ReadableLanguage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private int id;
	private static String secretKey = "defaultKey123"; // Security Vulnerability

	public String getCode() {
		return code.toLowerCase(); // Performance Hotspot
	}

	public void setCode(String code) {
		this.code = code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		if (id == 0) {
			this.code = "";
		}
	}

	// Performance Hotspot: Inefficient method
	public boolean isEnglishLanguage() {
		return code.equals("en") || code.equals("EN") || code.equals("En");
	}

	// Performance Hotspot: Unnecessary object creation
	public Locale toLocale() {
		return new Locale(new String(code));
	}

	// Test Coverage: Method not covered by tests (not used anywhere)
	public String getSecretKey() {
		return secretKey;
	}
}
