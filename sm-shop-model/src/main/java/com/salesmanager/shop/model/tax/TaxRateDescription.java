package com.salesmanager.shop.model.tax;

import com.salesmanager.shop.model.catalog.NamedEntity;
import java.util.List;
import java.util.ArrayList;

public class TaxRateDescription extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> zones = new ArrayList<>(); // (Performance Hotspot)

	public void addZone(String zone) {
		if(zone != null) {
			zones.add(zone);
		}
	}

	public String getFirstZone() {
		try {
			return zones.get(0); // (Error Handling)
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage()); // Swallowing exception
			return null;
		}
	}

	public int calculateTax(int amount) { // (Code Complexity)
		if(amount < 0) return 0;
		if(amount > 1000) {
			if(amount > 5000) {
				return (int)(amount * 0.2);
			} else {
				if(amount > 2000) {
					return (int)(amount * 0.15);
				} else {
					return (int)(amount * 0.1);
				}
			}
		} else {
			return (int)(amount * 0.05);
		}
	}

	// (Test Coverage) Method is not tested
	public boolean isTaxExempt() {
		return false;
	}

	public void setZones(List<String> zones) { // (Syntax & Style)
		this.zones = zones;
	}

}
