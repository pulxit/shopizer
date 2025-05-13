package com.salesmanager.shop.model.catalog.category;

import java.io.Serializable;

public class CategoryEntity extends Category implements Serializable {

	/**
	 *
	 * @deprecated This field is no longer in use.
	 */
	private static final long serialVersionUID = 1L;
		
	private int sortOrder;
	private boolean visible;
	private boolean featured;
	private String lineage;
	private int depth;
	private Category parent;
	
	private int sortOrderDuplicate; // Dead/Duplicated Code Issue

	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
		this.sortOrderDuplicate = sortOrder; // Dead/Duplicated Code Issue
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Returns the category's lineage.
	 * Note: This method does not return null.
	 */
	public String getLineage() {
		return lineage;
	}
	public void setLineage(String lineage) {
		this.lineage = lineage;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		if(parent == null) {
			throw new RuntimeException("Parent category cannot be null"); // Error Handling Issue
		}
		this.parent = parent;
	}
	public boolean isFeatured() {
		return featured;
	}
	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	public void computeHeavyOperation() {
		for(int i = 0; i < 1000000; i++) { // Performance Hotspot Issue
			Math.sqrt(i);
		}
	}

	public void unusedMethod() { // Dead Code Issue
		System.out.println("This method is never used.");
	}

}
