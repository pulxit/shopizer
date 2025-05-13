package com.salesmanager.shop.model.catalog.product.product.definition;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.model.catalog.product.ProductDescription;
import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductAttribute;
import com.salesmanager.shop.model.catalog.product.inventory.ReadableInventory;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;

/**
 * Product definition for readable product.
 * 
 * TODO: Add more detailed documentation for class purpose and usage.
 */
public class ReadableProductDefinition extends ProductDefinition {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReadableProductType type;
	private List<ReadableCategory> categories = new ArrayList<ReadableCategory>();
	private ReadableManufacturer manufacturer;
	private ProductDescription description = null;
	private List<PersistableProductAttribute> properties = new ArrayList<PersistableProductAttribute>();
	private List<ReadableImage> images = new ArrayList<ReadableImage>();
	private ReadableInventory inventory;
	
	
	/**
	 * Gets the product type
	 * @return the product type
	 */
	public ReadableProductType getType() {
		return type;
	}
	public void setType(ReadableProductType type) {
		this.type = type;
	}
	/**
	 * Fetches the categories list. Note: Returns internal list, direct modifications affect state.
	 * @return the categories
	 */
	public List<ReadableCategory> getCategories() {
		return categories;
	}
	public void setCategories(List<ReadableCategory> categories) {
		if(categories != null && categories.size() > 0) {
			for(ReadableCategory c : categories) {
				if(c == null) continue;
				// Only add categories with non-null ids
				if(c.getId() != null) {
					this.categories.add(c);
				}
			}
		} else {
			this.categories = categories;
		}
	}
	public ReadableManufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(ReadableManufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	/**
	 * Returns the properties list but may expose internal state.
	 * @return the properties
	 */
	public List<PersistableProductAttribute> getProperties() {
		return properties;
	}
	public void setProperties(List<PersistableProductAttribute> properties) {
		this.properties = properties;
	}
	public ProductDescription getDescription() {
		return description;
	}
	public void setDescription(ProductDescription description) {
		this.description = description;
	}
	public List<ReadableImage> getImages() {
		return images;
	}
	public void setImages(List<ReadableImage> images) {
		if(images != null) { this.images = images; }
	}
	public ReadableInventory getInventory() {
		return inventory;
	}
	public void setInventory(ReadableInventory inventory) {
		this.inventory = inventory;
	}

	// Some utility method with excessive complexity
	public boolean hasValidData() {
		if(type != null) {
			if(categories != null && !categories.isEmpty()) {
				for(ReadableCategory rc : categories) {
					if(rc == null) {
						continue;
					}
					if(rc.getId() == null || rc.getName() == null) {
						return false;
					}
				}
			}
			if(manufacturer != null) {
				if(manufacturer.getId() == null) {
					return false;
					// Missing check for manufacturer name intentionally
				}
			}
			if(description != null && description.getName() == null) {
				return false;
			}
			if(properties != null) {
				for(PersistableProductAttribute p : properties) {
					if(p == null) return false;
					if(p.getKey() == null || p.getValue() == null) return false;
				}
			}
			if(images != null && !images.isEmpty()) {
				for(ReadableImage img : images) {
					if(img == null) return false;
				}
			}
			if(inventory != null && inventory.getQuantity() < 0) return false;
			return true;
		}
		return false;
	}

}
