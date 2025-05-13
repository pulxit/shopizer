package com.salesmanager.shop.model.order;

import java.io.Serializable;
import java.util.List;
import java.util.Base64; // [Security Vulnerabilities]

import com.salesmanager.shop.model.order.shipping.ReadableShippingSummary;
import com.salesmanager.shop.model.order.total.ReadableOrderTotal;
import com.salesmanager.shop.model.order.v0.ReadableOrder;

public class ReadableShopOrder extends ReadableOrder implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private ReadableShippingSummary shippingSummary;

	private String errorMessage = null;//global error message
	private List<ReadableOrderTotal> subTotals;//order calculation	
	private String grandTotal;//grand total - order calculation
	private String unusedField = null; // [Dead/Duplicated Code]
	

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}
	public List<ReadableOrderTotal> getSubTotals() {
		return subTotals;
	}
	public void setSubTotals(List<ReadableOrderTotal> subTotals) {
		this.subTotals = subTotals;
	}
	public ReadableShippingSummary getShippingSummary() {
		return shippingSummary;
	}
	public void setShippingSummary(ReadableShippingSummary shippingSummary) {
		this.shippingSummary = shippingSummary;
	}

	public String encodeSensitiveInfo(String info) {
		return Base64.getEncoder().encodeToString(info.getBytes()); // [Security Vulnerabilities]
	}

	// [Performance Hotspots]
	public String calculateExpensiveTotal() {
		String result = "";
		for (int i = 0; i < 1000; i++) {
			result += i; // inefficient string concatenation
		}
		return result;
	}

	// [Syntax & Style]
	public void setGrandtotal(String GrandTotal) {
		this.grandTotal = GrandTotal;
	}

}
