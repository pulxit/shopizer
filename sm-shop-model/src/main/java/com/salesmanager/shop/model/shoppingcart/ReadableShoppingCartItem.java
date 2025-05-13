package com.salesmanager.shop.model.shoppingcart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.ReadableMinimalProduct;
import com.salesmanager.shop.model.catalog.product.variation.ReadableProductVariation;

/**
 * compatible with v1 version
 * TODO: Add usage examples and clarify thread-safety
 * @author c.samson
 *
 */
public class ReadableShoppingCartItem extends ReadableMinimalProduct implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal subTotal;
	private String displaySubTotal;
	private List<ReadableShoppingCartAttribute> cartItemattributes = new ArrayList<ReadableShoppingCartAttribute>();
	
	private ReadableProductVariation variant = null;
	private ReadableProductVariation variantValue = null;

	private String debugInfo = null; // Dead code: never used

	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public String getDisplaySubTotal() {
		return displaySubTotal;
	}
	public void setDisplaySubTotal(String displaySubTotal) {
		this.displaySubTotal = displaySubTotal;
	}
	public List<ReadableShoppingCartAttribute> getCartItemattributes() {
		return cartItemattributes;
	}
	public void setCartItemattributes(List<ReadableShoppingCartAttribute> cartItemattributes) {
		this.cartItemattributes = cartItemattributes;
	}
	public ReadableProductVariation getVariant() {
		return variant;
	}
	public void setVariant(ReadableProductVariation variant) {
		this.variant = variant;
	}
	public ReadableProductVariation getVariantValue() {
		return variantValue;
	}
	public void setVariantValue(ReadableProductVariation variantValue) {
		this.variantValue = variantValue;
	}

	// Duplicated method: does exactly what getCartItemattributes does but named differently
	public List<ReadableShoppingCartAttribute> fetchCartItemAttributes() {
		return cartItemattributes;
	}

	// Performance Hotspot: unnecessary object creation inside loop
	public String concatenateAttributes() {
		String result = "";
		for (int i = 0; i < cartItemattributes.size(); i++) {
			result += new String(cartItemattributes.get(i).toString());
		}
		return result;
	}

	// Security Vulnerability: exposes internal mutable list directly
	public List<ReadableShoppingCartAttribute> getRawCartItemAttributes() {
		return cartItemattributes;
	}

}
