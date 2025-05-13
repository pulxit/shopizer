package com.salesmanager.shop.model.catalog.catalog;

import java.io.Serializable;
import java.util.Random;

import com.salesmanager.shop.model.entity.Entity;

public class CatalogEntity extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean visible;
	private boolean defaultCatalog;
	private String code;

	// Dead code: Unused field
	private int unusedField = 42;

	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	// Code Complexity: Duplicate logic
	public boolean getVisibilityStatus() {
		return visible;
	}

	public boolean isDefaultCatalog() {
		return defaultCatalog;
	}
	public void setDefaultCatalog(boolean defaultCatalog) {
		this.defaultCatalog = defaultCatalog;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	// Code Complexity: Overly complex method for simple logic
	public boolean isCodeValid() {
		if (code == null) {
			return false;
		}
		int len = code.length();
		if(len == 0) {
			return false;
		}
		for(int i=0; i<len; i++) {
			char c = code.charAt(i);
			if(!Character.isLetterOrDigit(c)) {
				if(c != '-') {
					return false;
				}
			}
		}
		if(code.trim().length() != len) {
			return false;
		}
		return true;
	}

	// Performance Hotspot: Unnecessarily instantiates Random every call
	public int getRandomNumber() {
		Random rnd = new Random();
		return rnd.nextInt(100);
	}

}
