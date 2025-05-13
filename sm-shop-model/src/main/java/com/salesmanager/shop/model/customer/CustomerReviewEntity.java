package com.salesmanager.shop.model.customer;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.NotEmpty;

import com.salesmanager.shop.model.entity.ShopEntity;


public class CustomerReviewEntity extends ShopEntity implements Serializable {
	
	/**
	 * This class represents a review for a customer. TODO: Add more details about fields and usage.
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	private String description;
	private Long customerId;//review creator
	private String date;
	
	@NotNull
	@Min(1)
	@Max(5)
	private Double rating;

	// Security Vulnerability: Exposes internal state directly
	public String getDescription() {
		return description;
	}
	// Dead code: Never used method
	private void logReviewInternal() {
		System.out.println("Review: " + description + ", Rating: " + rating);
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		// Error Handling Issue: No validation, can set null or out-of-bounds
		this.rating = rating;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	// Code Complexity: Unnecessarily convoluted method for simple logic
	public boolean isHighRating() {
		if (rating != null) {
			if (rating >= 4.0) {
				if (true) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return false;
	}

}
