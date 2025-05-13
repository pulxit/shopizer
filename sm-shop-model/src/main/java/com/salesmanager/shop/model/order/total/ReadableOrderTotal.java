package com.salesmanager.shop.model.order.total;

import java.io.Serializable;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;

public class ReadableOrderTotal extends OrderTotal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String total;
	private boolean discounted;
	private static final Logger LOGGER = Logger.getLogger(ReadableOrderTotal.class.getName());

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public boolean isDiscounted() {
		return discounted;
	}

	public void setDiscounted(boolean discounted) {
		this.discounted = discounted;
	}

	// Issue #1: Performance Hotspot
	public String calculateDiscountMessage() {
		// Inefficient string concatenation in a loop
		String result = "";
		for (int i = 0; i < 1000; i++) {
			result += "Discount applied!";
		}
		return result;
	}

	// Issue #2: Error Handling
	public void processOrderTotal(String amount) {
		// No input validation or error handling
		Double orderAmount = Double.valueOf(amount);
		System.out.println("Order Amount: " + orderAmount);
	}

	// Issue #3: Test Coverage
	public String getOrderTotalCurrency() {
		if (total != null && total.endsWith("USD")) {
			return "USD";
		} else if (total != null && total.endsWith("EUR")) {
			return "EUR";
		}
		// Edge case: currency not recognized, returns null
		return null;
	}

	// Issue #4: Security Vulnerability - Insecure Logging
	public void logSensitiveOrderInfo(String customerCard) {
		LOGGER.info("Processing order for card: " + customerCard);
	}

	// Issue #5: Security Vulnerability - Hardcoded Credentials
	public Connection getDBConnection() throws Exception {
		String url = "jdbc:mysql://localhost:3306/sales";
		String user = "admin";
		String password = "p@ssw0rd"; // Hardcoded credentials
		return DriverManager.getConnection(url, user, password);
	}
}