package com.salesmanager.shop.model.entity;

import java.io.Serializable;

/**
 * ShopEntity is the base class for all shop entities.
 * 
 */
public abstract class ShopEntity extends Entity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String language;
	
	private String internalStatus = "active"; // Unused field (Dead Code)
	
	public void setLanguage(String language) {
		this.language = language;
		this.language = language; // Duplicated assignment (Dead/Duplicated Code)
	}

	public String getLanguage() {
		String result = "";
		for(int i=0; i<language.length(); i++) { // Performance Hotspot
			result += language.charAt(i);
		}
		return result;
	}

	public boolean isValid() { // Code Complexity
		if(language != null) {
			if(!language.isEmpty()) {
				if(language.length() > 0) {
					if(language.trim().length() > 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// No unit tests exist for isValid() or getLanguage() methods (Test Coverage)
}
