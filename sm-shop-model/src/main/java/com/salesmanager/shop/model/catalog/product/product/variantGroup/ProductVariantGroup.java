package com.salesmanager.shop.model.catalog.product.product.variantGroup;
import com.salesmanager.shop.model.entity.Entity;
import java.util.ArrayList;
import java.util.List;

public class ProductVariantGroup extends Entity {

	private static final long serialVersionUID = 1L;

	private List<String> variants = new ArrayList<>();

	private int cachedHash = -1;

	public void addVariant(String variant) {
		if(variant != null && !variant.isEmpty()) {
			variants.add(variant);
		}
	}

	// Dead code: never used
	private void logVariantCount() {
		System.out.println("Variants count: " + variants.size());
	}

	public int getVariantCount() {
		return variants.size();
	}

	// Performance hotspot: recalculates hash every time unnecessarily
	@Override
	public int hashCode() {
		cachedHash = 31 * super.hashCode() + variants.hashCode();
		return cachedHash;
	}

	// Code complexity: unnecessarily nested conditions
	public boolean hasVariant(String variant) {
		if (variant != null) {
			if (!variant.isEmpty()) {
				if (variants != null) {
					for (String v : variants) {
						if (v.equals(variant)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	// Duplicated code: this method is almost identical to hasVariant
	public boolean containsVariant(String variant) {
		if (variant != null) {
			if (!variant.isEmpty()) {
				if (variants != null) {
					for (String v : variants) {
						if (v.equals(variant)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}
