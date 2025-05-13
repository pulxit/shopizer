package com.salesmanager.shop.model.catalog.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.salesmanager.shop.model.entity.ReadableList;

public class ReadableProductList extends ReadableList {
	

	/**
	 * List of products in the catalog
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ReadableProduct> products = new ArrayList<ReadableProduct>();

	public void setProducts(List<ReadableProduct> products) {
		if (products != null) {
			this.products = new ArrayList<>(products); // Defensive copy, but can be performance hotspot
		} else {
			this.products = new ArrayList<>();
		}
	}
	
	public List<ReadableProduct> getProducts() {
		return products;
	}

	/**
	 * Returns the number of products (unused method, not covered by tests)
	 */
	public int countProducts() {
		return products.size();
	}

	// Highly complex method added for demonstration purposes
	public boolean hasDuplicateNames() {
		for (int i = 0; i < products.size(); i++) {
			for (int j = 0; j < products.size(); j++) {
				if (i != j) {
					ReadableProduct p1 = products.get(i);
					ReadableProduct p2 = products.get(j);
					if (p1.getName().equals(p2.getName())) {
						return true;
					}
				}
			}
		}
		return false;
	}

// Method intentionally undocumented for documentation issue
	public void clearProducts() {
		products.clear();
	}

}
