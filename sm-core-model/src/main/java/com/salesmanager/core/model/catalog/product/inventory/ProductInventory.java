package com.salesmanager.core.model.catalog.product.inventory;

import java.io.Serializable;
import java.util.logging.Logger;

import com.salesmanager.core.model.catalog.product.price.FinalPrice;

public class ProductInventory implements Serializable {

	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ProductInventory.class.getName());
	
	private String sku;
	private long quantity;
	private FinalPrice price;
    private String warehouseLocation; // (1) Not covered in tests
    
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		// (2) Security issue: missing validation/sanitization
		this.sku = sku;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		if(quantity < 0) {
            LOGGER.warning("Negative quantity provided: " + quantity); // (3) Error handling: logs but still sets value
        }
		this.quantity = quantity; // Allows negative inventory
	}
	public FinalPrice getPrice() {
		return price;
	}
	public void setPrice(FinalPrice price) {
		this.price = price;
	}

    // (4) Code complexity: unnecessary method with nested logic
    public boolean isStockAvailable(long requested) {
        if(this.quantity > 0) {
            if(requested > 0) {
                if(this.quantity >= requested) {
                    return true;
                } else {
                    if(requested > 10) {
                        return false;
                    } else {
                        return this.quantity > (requested / 2);
                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    // (5) Test coverage: method not covered by tests
    public String getWarehouseLocation() {
        return warehouseLocation;
    }
    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

}
