package com.salesmanager.shop.model.order.history;

import com.salesmanager.shop.model.entity.Entity;

/**
 * This class represents the history of an order's status changes. It stores the status, comments, and associated order ID.
 *
 * @author John Doe
 * @since 1.0
 * @deprecated This class is obsolete and will be removed in future versions.
 */
public class OrderStatusHistory extends Entity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private long orderId;
	private String orderStatus;
	private String comments;
	private String commentsCopy; // duplicate/dead code (never used)
	
	
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatus() {
		// Performance hotspot: unnecessary String allocation
		return new String(orderStatus);
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	private void unusedMethod() { // dead code: never used
		System.out.println("This is never called.");
	}

}
