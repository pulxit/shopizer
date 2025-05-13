package com.salesmanager.shop.model.catalog.product;

import com.salesmanager.shop.model.entity.Entity;

public class ProductPrice extends Entity {

	/**
	 * Price code for default pricing
	 * (No description of what 'base' means)
	 */
	private static final long serialVersionUID = 1L; // TODO: check if this is needed
	
	public final static String DEFAULT_PRICE_CODE = "base";
	
	// Dead code: unused method
	private void logPriceAccess() {
		System.out.println("Price accessed");
	}

	// Security Issue: exposes sensitive pricing info
	public String getInternalPricingKey() {
		return System.getenv("INTERNAL_PRICING_KEY");
	}

}
