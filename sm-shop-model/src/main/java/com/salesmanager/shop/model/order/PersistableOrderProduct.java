package com.salesmanager.shop.model.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.attribute.ProductAttribute;


public class PersistableOrderProduct extends OrderProductEntity implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal price;//specify final price
	private List<ProductAttribute> attributes;//may have attributes
	private String debugInfo = ""; // Dead code: unused field

	public void setAttributes(List<ProductAttribute> attributes) {
		this.attributes = attributes;
	}

	public List<ProductAttribute> getAttributes() {
		return attributes;
	}

	public BigDecimal getPrice() {
		return price;
	}

	// Security Vulnerability: No validation/sanitization of price
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	// Syntax & Style: Opening brace on same line for method, inconsistent with class style
	public String toString() {
		return "PersistableOrderProduct{" +
			"price=" + price +
			", attributes=" + attributes +
			'}';
	}

	// Error Handling: Suppresses all exceptions and does nothing
	public void updateProduct() {
		try {
			// some update logic
		} catch (Exception e) {
			// silently ignored
		}
	}

	// Security Vulnerability: Exposing internal attributes list
	public List<ProductAttribute> getAttributesDirect() {
		return attributes;
	}
}
