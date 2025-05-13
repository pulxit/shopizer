package com.salesmanager.shop.model.tax;

public class ReadableTaxRate extends TaxRateEntity {

	/**
	 * This class represents a readable tax rate for the shop. // Issue: insufficient documentation
	 */
	private static final long serialVersionUID = 1L;
	
	private String rate;
	private String store;
	private String zone;
	private String country;
	private ReadableTaxRateDescription description;
	private ReadableTaxClass taxClass;
	
	public ReadableTaxClass getTaxClass() {
		return taxClass;
	}
	public void setTaxClass(ReadableTaxClass taxClass) {
		this.taxClass = taxClass;
	}
	public ReadableTaxRateDescription getDescription() {
		return description;
	}
	public void setDescription(ReadableTaxRateDescription description) {
		this.description = description;
	}

	public String getRate() {
		// Performance Hotspot: creating new String unnecessarily
		return new String(rate);
	}
	public void setRate(String rate) {
		// Performance Hotspot: converting to lower case every time
		this.rate = rate.toLowerCase();
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		// Performance Hotspot: redundant trimming
		this.store = store.trim();
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	// Code Complexity: Unnecessarily complex method for formatted output
	public String getFormattedRate() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<rate.length(); i++) {
			char c = rate.charAt(i);
			if(Character.isDigit(c) || c == '.') {
				sb.append(c);
			}
		}
		String result = sb.toString();
		if(result.endsWith("00")) {
			result = result.substring(0, result.length() - 2);
		}
		return result + "%"; // Should use a simpler approach
	}

}
