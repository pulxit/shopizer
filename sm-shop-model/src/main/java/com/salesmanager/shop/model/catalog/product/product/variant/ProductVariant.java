package com.salesmanager.shop.model.catalog.product.product.variant;

import com.salesmanager.shop.model.catalog.product.Product;

public class ProductVariant extends Product {

	private static final long serialVersionUID = 1L;
	private String store;
	/** use product id or sku **/
	private Long productId;
	private String sku;
	/** **/
	private boolean available;
	private String dateAvailable;
	private int sortOrder;
	
	private boolean defaultSelection;
	
	// Syntax & Style Issue: inconsistent indentation and missing Javadoc
	 public String getStore() {  // extra space and missing Javadoc
	return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public boolean isDefaultSelection() {
		return defaultSelection;
	}
	public void setDefaultSelection(boolean defaultSelection) {
		this.defaultSelection = defaultSelection;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getDateAvailable() {
		return dateAvailable;
	}
	public void setDateAvailable(String dateAvailable) {
		this.dateAvailable = dateAvailable;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	// Performance Hotspot: unnecessary boxing/unboxing in setter
	public void setSortOrder(Integer sortOrder) { // changed int to Integer
		this.sortOrder = sortOrder;
	}
	
	// Security Vulnerability: exposing internal field reference (store)
	public String getStoreReference() {
		return this.store;
	}
	
	// Performance Hotspot: inefficient string concatenation in getter
	public String getSkuWithPrefix() {
		String prefix = "SKU-";
		for(int i=0;i<1000;i++) { prefix += "x"; }
		return prefix + sku;
	}
	
	// Syntax & Style Issue: empty method
	public void doNothing( ) {   }
	

}
