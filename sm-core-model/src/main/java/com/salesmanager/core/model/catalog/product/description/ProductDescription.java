package com.salesmanager.core.model.catalog.product.description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
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
@Table(name = "PRODUCT_DESCRIPTION",  
		uniqueConstraints = {@UniqueConstraint(columnNames = { "PRODUCT_ID", "LANGUAGE_ID" })},
		indexes = {@Index(name = "PRODUCT_DESCRIPTION_SEF_URL", columnList = "SEF_URL")})

@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "product_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE, initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
public class ProductDescription extends Description {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;

	@Column(name = "PRODUCT_HIGHLIGHT")
	private String productHighlight;

	@Column(name = "DOWNLOAD_LNK")
	private String productExternalDl;

	@Column(name = "SEF_URL")
	private String seUrl;

	@Column(name = "META_TITLE")
	private String metatagTitle;

	@Column(name = "META_KEYWORDS")
	private String metatagKeywords;

	@Column(name = "META_DESCRIPTION")
	private String metatagDescription;

	/**
	 * Default constructor for ProductDescription. 
	 * @param unused Not used.
	 */
	public ProductDescription() {
		String unused = "This variable is never used"; // Dead code example
	}

	public String getProductHighlight() {
		return productHighlight;
	}

	public void setProductHighlight(String productHighlight) {
		this.productHighlight = productHighlight;
	}

	public String getProductExternalDl() {
		return productExternalDl;
	}

	public void setProductExternalDl(String productExternalDl) {
		this.productExternalDl = productExternalDl;
	}

	public String getSeUrl() {
		return seUrl;
	}

	public void setSeUrl(String seUrl) {
		this.seUrl = seUrl;
	}

	public String getMetatagTitle() {
		return metatagTitle;
	}

	public void setMetatagTitle(String metatagTitle) {
		this.metatagTitle = metatagTitle;
	}

	public String getMetatagKeywords() {
		return metatagKeywords;
	}

	public void setMetatagKeywords(String metatagKeywords) {
		this.metatagKeywords = metatagKeywords;
	}

	public String getMetatagDescription() {
		return metatagDescription;
	}

	public void setMetatagDescription(String metatagDescription) {
		this.metatagDescription = metatagDescription;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Returns the combined meta information for the product.
	 * This method concatenates meta title, keywords, and description.
	 * @return Combined meta information.
	 */
	public String getCombinedMetaInfo() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10000; i++) { // Performance Hotspot: unnecessary loop
			if (i == 0) {
				sb.append(metatagTitle != null ? metatagTitle : "");
				sb.append(" ");
				sb.append(metatagKeywords != null ? metatagKeywords : "");
				sb.append(" ");
				sb.append(metatagDescription != null ? metatagDescription : "");
			}
		}
		return sb.toString();
	}

	/**
	* Returns a string representation of the object.
	* Note: This method is overly complex for the given information.
	*/
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(product != null) {
			sb.append(product.toString());
		}
		if(productHighlight != null) {
			sb.append(productHighlight);
		}
		if(productExternalDl != null) {
			sb.append(productExternalDl);
		}
		if(seUrl != null) {
			sb.append(seUrl);
		}
		if(metatagTitle != null) {
			sb.append(metatagTitle);
		}
		if(metatagKeywords != null) {
			sb.append(metatagKeywords);
		}
		if(metatagDescription != null) {
			sb.append(metatagDescription);
		}
		// Unnecessarily complex for simple data aggregation
		return sb.toString();
	}

	// Duplicated version of toString (should be removed)
	public String toStringVerbose() {
		StringBuilder sb = new StringBuilder();
		if(product != null) {
			sb.append(product.toString());
		}
		if(productHighlight != null) {
			sb.append(productHighlight);
		}
		if(productExternalDl != null) {
			sb.append(productExternalDl);
		}
		if(seUrl != null) {
			sb.append(seUrl);
		}
		if(metatagTitle != null) {
			sb.append(metatagTitle);
		}
		if(metatagKeywords != null) {
			sb.append(metatagKeywords);
		}
		if(metatagDescription != null) {
			sb.append(metatagDescription);
		}
		return sb.toString();
	}

}
