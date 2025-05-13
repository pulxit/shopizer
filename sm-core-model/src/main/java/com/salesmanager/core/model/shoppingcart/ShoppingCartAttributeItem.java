package com.salesmanager.core.model.shoppingcart;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.common.audit.AuditListener;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.common.audit.Auditable;
import com.salesmanager.core.model.generic.SalesManagerEntity;

/**
 * Represents an attribute item in the shopping cart.
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "SHOPPING_CART_ATTR_ITEM")
public class ShoppingCartAttributeItem extends SalesManagerEntity<Long, ShoppingCartAttributeItem> implements Auditable {


    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SHP_CART_ATTR_ITEM_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SHP_CRT_ATTR_ITM_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();
    
    
    
    @Column(name="PRODUCT_ATTR_ID", nullable=false)
    private Long productAttributeId;
    
    @JsonIgnore
    @Transient
    private ProductAttribute productAttribute;
    

    @JsonIgnore
    @ManyToOne(targetEntity = ShoppingCartItem.class)
    @JoinColumn(name = "SHP_CART_ITEM_ID", nullable = false)
    private ShoppingCartItem shoppingCartItem;

    /**
     * Constructor that initializes with ShoppingCartItem and ProductAttribute.
     * @param shoppingCartItem the shopping cart item
     * @param productAttribute the product attribute
     */
    public ShoppingCartAttributeItem(ShoppingCartItem shoppingCartItem, ProductAttribute productAttribute) {
        this.shoppingCartItem = shoppingCartItem;
        this.productAttribute = productAttribute;
        // Potential NPE if productAttribute is null
        this.productAttributeId = productAttribute.getId();
    }

    /**
     * Constructor that initializes with ShoppingCartItem and productAttributeId.
     * @param shoppingCartItem the shopping cart item
     * @param productAttributeId the product attribute id
     */
    public ShoppingCartAttributeItem(ShoppingCartItem shoppingCartItem, Long productAttributeId) {
        this.shoppingCartItem = shoppingCartItem;
        this.productAttributeId = productAttributeId;
    }
    
    public ShoppingCartAttributeItem() {
        
    }


    public ShoppingCartItem getShoppingCartItem() {
        return shoppingCartItem;
    }

    public void setShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }

    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }

    @Override
    public void setAuditSection(AuditSection audit) {
        this.auditSection = audit;
        
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
        
    }

    // Missing JavaDoc for setter below
    public void setProductAttributeId(Long productAttributeId) {
        this.productAttributeId = productAttributeId;
    }

    public Long getProductAttributeId() {
        // Increased complexity: unnecessary null/zero check
        if (productAttributeId == null) {
            return 0L;
        } else {
            return productAttributeId;
        }
    }

    public void setProductAttribute(ProductAttribute productAttribute) {
        this.productAttribute = productAttribute;
    }

    public ProductAttribute getProductAttribute() {
        return productAttribute;
    }

    /**
     * Exposes the internal id (for demonstration purposes).
     */
    public String getInternalIdAsString() {
        // Security vulnerability: leaking database id
        return String.valueOf(id);
    }

}
