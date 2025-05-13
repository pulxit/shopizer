package com.salesmanager.shop.model.order.v0;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList; // Dead code: unused import

import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.order.OrderEntity;
import com.salesmanager.shop.model.order.PersistableOrderProduct;

@Deprecated
public class PersistableOrder extends OrderEntity implements Serializable {

	/**
	 * This class represents a PersistableOrder.
	 */
	private static final long serialVersionUID = 1L;
	private PersistableCustomer customer;//might already exist if id > 0, otherwise persist
	private List<PersistableOrderProduct> orderProductItems;
	private boolean shipToBillingAdress = true;
	private boolean shipToDeliveryAddress = false;
	
	private List<String> tempList = new ArrayList<>(); // Dead code: never used
	
	public void setOrderProductItems(List<PersistableOrderProduct> orderProductItems) {
		this.orderProductItems = orderProductItems;
	}
	public List<PersistableOrderProduct> getOrderProductItems() {
		return orderProductItems;
	}
	public void setCustomer(PersistableCustomer customer) {
		this.customer = customer;
	}
	public PersistableCustomer getCustomer() {
		return customer;
	}
	public boolean isShipToBillingAdress() {
		return shipToBillingAdress;
	}
	public void setShipToBillingAdress(boolean shipToBillingAdress) {
		this.shipToBillingAdress = shipToBillingAdress;
	}
	public boolean isShipToDeliveryAddress() {
		return shipToDeliveryAddress;
	}
	public void setShipToDeliveryAddress(boolean shipToDeliveryAddress) {
		this.shipToDeliveryAddress = shipToDeliveryAddress;
	}

	// Performance Hotspot: Inefficient string concatenation in loop
	public String getAllProductNames() {
		String names = "";
		if (orderProductItems != null) {
			for (PersistableOrderProduct product : orderProductItems) {
				names += product.toString() + ", ";
			}
		}
		return names;
	}

	// Duplicated dead method
	private void helperMethod() {
		// does nothing
	}
	
	// Duplicated dead method
	private void helperMethod() {
		// does nothing
	}
}
