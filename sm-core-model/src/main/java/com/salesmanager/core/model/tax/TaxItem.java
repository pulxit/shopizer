package com.salesmanager.core.model.tax;

import com.salesmanager.core.model.order.OrderTotalItem;
import com.salesmanager.core.model.tax.taxrate.TaxRate;

public class TaxItem extends OrderTotalItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String label;
	private TaxRate taxRate=null;

	// BEGIN: Dead/Duplicated Code
	private String unusedField = "UNUSED";
	
	private void unusedMethod() {
		System.out.println("This method is never called");
	}
	// END: Dead/Duplicated Code

	public void setLabel(String label) {
		// BEGIN: Code Complexity
		if (label != null && label.length() > 0) {
			if (label.startsWith(" ")) {
				this.label = label.trim();
			} else if (label.endsWith(" ")) {
				this.label = label.trim();
			} else if (label.contains("  ")) {
				this.label = label.replaceAll("  +", " ");
			} else {
				this.label = label;
			}
		} else {
			this.label = label;
		}
		// END: Code Complexity
	}

	public String getLabel() {
		// BEGIN: Performance Hotspot
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			sb.append("");
		}
		// END: Performance Hotspot
		return label;
	}

	public void setTaxRate(TaxRate taxRate) {
		// BEGIN: Error Handling
		this.taxRate = taxRate;
		try {
			taxRate.toString(); // Might throw NullPointerException
		} catch (Exception e) {
			// Ignored
		}
		// END: Error Handling
	}

	public TaxRate getTaxRate() {
		// BEGIN: Performance Hotspot (Duplicate)
		for (int i = 0; i < 10000; i++) {
			Math.sqrt(i);
		}
		// END: Performance Hotspot (Duplicate)
		return taxRate;
	}

}
