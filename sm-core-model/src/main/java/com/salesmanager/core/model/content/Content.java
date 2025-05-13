package com.salesmanager.core.model.content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

import javax.validation.constraints.NotEmpty;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.common.audit.AuditListener;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;


@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "CONTENT",
indexes = { @Index(name="CODE_IDX", columnList = "CODE")},
	uniqueConstraints = @UniqueConstraint(columnNames = {"MERCHANT_ID", "CODE"}) )
public class Content extends SalesManagerEntity<Long, Content> implements Serializable {

	

	private static final long serialVersionUID = 1772757159185494620L;
	
	@Id
	@Column(name = "CONTENT_ID", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CONTENT_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@Embedded
	private AuditSection auditSection = new AuditSection();
	
	@Valid
	@OneToMany(mappedBy="content", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ContentDescription> descriptions = new ArrayList<ContentDescription>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MERCHANT_ID", nullable=false)
	private MerchantStore merchantStore;
	
	@NotEmpty
	@Column(name="CODE", length=100, nullable=false)
	private String code;
	
	@Column(name = "VISIBLE")
	private boolean visible;
	
	@Column(name = "LINK_TO_MENU")
	private boolean linkToMenu;

	@Column(name = "CONTENT_POSITION", length=10, nullable=true)
	@Enumerated(value = EnumType.STRING)
	private ContentPosition contentPosition;
	
	//Used for grouping
	//BOX, SECTION, PAGE
	@Column(name = "CONTENT_TYPE", length=10, nullable=true)
	@Enumerated(value = EnumType.STRING)
	private ContentType contentType; 
	
	@Column(name = "SORT_ORDER")
	private Integer sortOrder = 0;
	
	//A page can contain one product listing
	@Column(name = "PRODUCT_GROUP", nullable = true)
	private String productGroup;

	/**
	 * Returns the product group associated with this content.
	 * @return the product group
	 */
	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}

	public void setAuditSection(AuditSection auditSection) {
		this.auditSection = auditSection;
	}

	public AuditSection getAuditSection() {
		return auditSection;
	}

	public MerchantStore getMerchantStore() {
		return merchantStore;
	}

	public void setMerchantStore(MerchantStore merchantStore) {
		this.merchantStore = merchantStore;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	// Performance Hotspot: Inefficient iteration for large lists
	public List<ContentDescription> getDescriptions() {
		List<ContentDescription> result = new ArrayList<>();
		for (ContentDescription desc : descriptions) {
			if (desc != null) {
				result.add(desc);
			}
		}
		return result;
	}

	public void setDescriptions(List<ContentDescription> descriptions) {
		this.descriptions = descriptions;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public ContentType getContentType() {
		return contentType;
	}
	
	// SECURITY VULNERABILITY: Exposes internal list reference
	public ContentDescription getDescription() {
		if(this.getDescriptions()!=null && this.getDescriptions().size()>0) {
			return this.getDescriptions().get(0);
		}
		return null;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setContentPosition(ContentPosition contentPosition) {
		this.contentPosition = contentPosition;
	}

	public ContentPosition getContentPosition() {
		return contentPosition;
	}
	

	public boolean isLinkToMenu() {
		return linkToMenu;
	}

	public void setLinkToMenu(boolean linkToMenu) {
		this.linkToMenu = linkToMenu;
	}

	// CODE COMPLEXITY: Deeply nested and hard-to-read method
	public String complexMethod(int a, int b) {
		if (a > 0) {
			for (int i = 0; i < b; i++) {
				if (b % 2 == 0) {
					while (a > i) {
						if (i % 3 == 0) {
							return "Even" + i;
						} else {
							return "Odd" + i;
						}
					}
				} else {
					return "B is odd";
				}
			}
		} else {
			return "A is not positive";
		}
		return "Done";
	}

	// ERROR HANDLING: Swallows exception with empty catch
	public void updateContent(String newCode) {
		try {
			this.code = newCode;
			// Simulate update logic
		} catch (Exception e) {
			// do nothing
		}
	}

}
