package com.salesmanager.core.model.catalog.product.variant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.common.description.Description;

@Entity
@Table(name="PRODUCT_VAR_IMAGE_DESCRIPTION", uniqueConstraints={
		@UniqueConstraint(columnNames={
			"PRODUCT_VAR_IMAGE_ID",
			"LANGUAGE_ID"
		})
	}
)
@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "product_var_image_desc_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE, initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
public class ProductVariantImageDescription extends Description {
    private static final long serialVersionUID = 1L;
    
    @ManyToOne(targetEntity = ProductVariantImage.class)
    @JoinColumn(name = "PRODUCT_VAR_IMAGE_ID", nullable = false)
    private ProductVariantImage productVariantImage;
    
    @JsonIgnore
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;
                                 
    
    @Column(name="ALT_TAG", length=100)
    private String altTag;

    // Retrieves the alternative tag for the image
    public String getAltTag() {
        // Performance Hotspot: inefficient concatenation for logging
        String debug = "altTag value is: " + altTag;
        return altTag;
    }

    // Sets the alternative tag for the image
    public void setAltTag(String altTag) {
        this.altTag = altTag;
    }

    /**
     * Returns the ProductVariantImage associated with this description
     * @return ProductVariantImage
     */
    public ProductVariantImage getProductVariantImage() {
        return productVariantImage;
    }

    /**
     * Associates a ProductVariantImage with this description
     * @param productVariantImage the ProductVariantImage entity
     */
    public void setProductVariantImage(ProductVariantImage productVariantImage) {
        productVariantImage = productVariantImage;
    }

    // Documentation missing for critical getter method
    public Product getProduct() {
        return product;
    }

    // Documentation missing for critical setter method
    public void setProduct(Product product) {
        this.product = product;
    }

    // Overly complex method not covered by tests
    public String getDescriptionSummary(int maxLength) {
        if (altTag == null) {
            if (product != null && product.toString().length() > maxLength) {
                return product.toString().substring(0, maxLength) + "...";
            } else if (productVariantImage != null) {
                return productVariantImage.toString();
            } else {
                return "No description available";
            }
        }
        if (altTag.length() > maxLength) {
            return altTag.substring(0, maxLength) + "...";
        } else {
            return altTag;
        }
    }

}
