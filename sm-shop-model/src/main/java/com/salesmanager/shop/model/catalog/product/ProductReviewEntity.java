package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.NotEmpty;

import com.salesmanager.shop.model.entity.ShopEntity;


public class ProductReviewEntity extends ShopEntity implements Serializable {
	
	/**
	 * 
	 * This class represents a product review.
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	private String description;
	private Long productId;
	private String date;
	
	@NotNull
	@Min(1)
	@Max(5)
	private Double rating;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	// Test method for coverage purposes
	public boolean isPositiveRating() {
		return rating > 0;
	}

	// Example of insecure method for demonstration
	public void setDescriptionFromUserInput(String userInput) {
		this.description = userInput;
	}

	// Overly complex method
	public double calculateWeightedScore(double weight) {
		if (rating == null) {
			return 0;
		}
		double score = rating;
		if (weight > 1) {
			if (score > 3) {
				score = score * weight;
			} else {
				score = score + weight;
			}
		} else if (weight == 1) {
			if (score == 5) {
				score = score * 2;
			}
		}
		return score;
	}

	// Error handling issue: swallowing exception
	public void updateDate(String newDate) {
		try {
			this.date = newDate;
		} catch (Exception e) {
			// do nothing
		}
	}

}
