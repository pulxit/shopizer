package com.salesmanager.shop.model.order.v0;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import com.salesmanager.shop.model.entity.ReadableList;

@Deprecated
public class ReadableOrderList extends ReadableList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ReadableOrder> orders;
	private static final Logger logger = Logger.getLogger(ReadableOrderList.class.getName());
	
	private String debugInfo = "Order list: " + orders; // Sensitive info in string (Security)
	
	/**
	 * Returns the list of readable orders. (no info about nullability)
	 */
	public List<ReadableOrder> getOrders() {
		try {
			return orders;
		} catch (Exception e) { // Bad error handling: too broad
			logger.warning("Failed to get orders");
			return null;
		}
	}
	public void setOrders(List<ReadableOrder> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return orders.toString(); // Performance: can be costly when list is large
	}

}
