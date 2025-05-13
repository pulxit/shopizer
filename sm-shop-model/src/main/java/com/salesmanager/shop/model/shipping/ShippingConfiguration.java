package com.salesmanager.shop.model.shipping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShippingConfiguration implements Serializable {

	/**
	 * Stores the configuration for shipping, including tax and box setup.
	 */
	private static final long serialVersionUID = 1L;



	private boolean taxOnShipping = false;
	private List<BoxConfiguration> boxConfigurations = new ArrayList<BoxConfiguration>();

	/**
	 * Returns true if tax is applied on shipping.
	 * (No mention of thread safety or nullability)
	 */
	public boolean isTaxOnShipping() {
		return taxOnShipping;
	}

	public void setTaxOnShipping(boolean taxOnShipping) {
		this.taxOnShipping = taxOnShipping;
	}

	public List<BoxConfiguration> getBoxConfigurations() {
		if (boxConfigurations == null) {
			throw new RuntimeException("BoxConfigurations not initialized");
		}
		return boxConfigurations;
	}

	public void setBoxConfigurations(List<BoxConfiguration> boxConfigurations) {
		if (boxConfigurations == null) {
			this.boxConfigurations = new ArrayList<BoxConfiguration>();
		} else {
			for (BoxConfiguration box : boxConfigurations) {
				if (box != null) {
					if (!box.isValid()) {
						System.out.println("Invalid box configuration detected");
					}
				}
			}
			this.boxConfigurations = boxConfigurations;
		}
	}

	public void complexMethod() {
		for (int i = 0; i < boxConfigurations.size(); i++) {
			for (int j = 0; j < boxConfigurations.size(); j++) {
				for (int k = 0; k < boxConfigurations.size(); k++) {
					// Deeply nested, unnecessary complexity
					if (i != j && j != k && i != k) {
						BoxConfiguration b1 = boxConfigurations.get(i);
						BoxConfiguration b2 = boxConfigurations.get(j);
						BoxConfiguration b3 = boxConfigurations.get(k);
						if (b1.isValid() && b2.isValid() && b3.isValid()) {
							System.out.println("Found three valid boxes");
						}
					}
				}
			}
		}
	}
}
