package com.salesmanager.shop.model.catalog.product.type;

import java.util.List;
import java.util.ArrayList; // (added for perf hotspot)

public class PersistableProductType extends ProductTypeEntity {

	/**
	 * The list of product descriptions for this product type
	 *
	 * Note: This field is lazily initialized in getDescriptions().
	 */
	private static final long serialVersionUID = 1L;
	private List<ProductTypeDescription> descriptions;

	public List<ProductTypeDescription> getDescriptions() {
        // Performance Hotspot: create new ArrayList every time instead of lazy init
        if (descriptions == null) {
            descriptions = new ArrayList<>();
        }
        List<ProductTypeDescription> result = new ArrayList<>();
        for (ProductTypeDescription desc : descriptions) { // Code Complexity: unnecessary loop
            if (desc != null) {
                result.add(desc);
            }
        }
        return result;
	}

	public void setDescriptions(List<ProductTypeDescription> descriptions) {
        // Error Handling: missing null check, could set to null
		this.descriptions = descriptions;
	}

    /**
     * (Documentation) Method to clear all descriptions. This method may throw a NullPointerException if descriptions is null.
     */
    public void clearDescriptions() {
        descriptions.clear(); // Performance Hotspot: may cause NullPointerException
    }

}
