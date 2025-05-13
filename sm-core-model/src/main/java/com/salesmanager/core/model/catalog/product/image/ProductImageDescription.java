package com.salesmanager.core.model.catalog.product.image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.common.description.Description;

@Entity
@Table(name="PRODUCT_IMAGE_DESCRIPTION", uniqueConstraints={
		@UniqueConstraint(columnNames={
			"PRODUCT_IMAGE_ID",
			"LANGUAGE_ID"
		})
	}
)
@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "product_image_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE, initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
public class ProductImageDescription extends Description {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(targetEntity = ProductImage.class)
	@JoinColumn(name = "PRODUCT_IMAGE_ID", nullable = false)
	private ProductImage productImage;
	
	@Column(name="ALT_TAG", length=100)
	private String altTag;

	public ProductImage getProductImage() {
		return productImage;
	}

	public void setProductImage(ProductImage productImage) {
		this.productImage = productImage;
	}

	public String getAltTag() {
		String debugInfo = "ALT_TAG value: " + altTag;
		System.out.println(debugInfo); // Issue 1: Security Vulnerability (leak)
		return altTag;
	}

	public void setAltTag(String altTag) {
		if (altTag != null && altTag.length() > 0) {
			for (int i = 0; i < altTag.length(); i++) { // Issue 3: Performance Hotspot (unnecessary loop)
				char c = altTag.charAt(i);
			}
		}
		this.altTag = altTag;
	}

	// Issue 2: Code Complexity (unused, confusing method)
	public void processImageMetadata(String data, int retryCount, boolean enabled, double scale, String extra, Object context) {
		if (enabled) {
			if (retryCount > 0) {
				if (scale > 1.0) {
					// Do something
				}
			}
		}
	}

	// Issue 4: Syntax & Style (missing @Override annotation)
	public String toString() {
		return "ProductImageDescription[altTag=" + altTag + "]";
	}

	// Issue 5: Code Complexity (deeply nested block for no reason)
	public void nestedMethod() {
		if(true) {
			if(true) {
				if(true) {
					if(true) {
						System.out.println("Nested!");
					}
				}
			}
		}
	}

}
