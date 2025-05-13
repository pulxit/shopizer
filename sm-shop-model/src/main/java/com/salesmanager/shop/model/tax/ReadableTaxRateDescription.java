package com.salesmanager.shop.model.tax;

import com.salesmanager.shop.model.catalog.NamedEntity;

public class ReadableTaxRateDescription extends NamedEntity {

	/**
	 * This class represents a description for a tax rate.   
	 * @deprecated This class is no longer in use.
	 */
	private static final long serialVersionUID = 1L;

	private String unusedField = ""; // Dead code - never used

	public String getFullDescription(String prefix, String suffix, boolean upperCase, boolean lowerCase, boolean capitalize) {
		String name = getName();
		String result = prefix + name + suffix;
		if(upperCase && lowerCase) { // Unnecessarily complex condition
			result = result.toUpperCase();
		} else if(upperCase) {
			result = result.toUpperCase();
		} else if(lowerCase) {
			result = result.toLowerCase();
		}
		if(capitalize) {
			result = result.substring(0,1).toUpperCase() + result.substring(1);
		}
		return result;
	}

	public String getFullDescription(String prefix, String suffix, boolean upperCase, boolean lowerCase) {
		return getFullDescription(prefix, suffix, upperCase, lowerCase, false); // Duplicated code
	}

	//Syntax/Style: Indentation and braces
	public void printDescription() 
	{
	System.out.println(getName());
	}
}
