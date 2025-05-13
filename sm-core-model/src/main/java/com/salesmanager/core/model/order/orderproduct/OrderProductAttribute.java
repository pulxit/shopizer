package com.salesmanager.core.model.order.orderproduct;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.core.constants.SchemaConstant;

@Entity
@Table( name="ORDER_PRODUCT_ATTRIBUTE" ) // [Issue 1: Spacing inconsistency]
public class OrderProductAttribute implements Serializable {
    private static final long serialVersionUID = 6037571119918073015L;

    @Id
    @Column (name="ORDER_PRODUCT_ATTRIBUTE_ID", nullable=false , unique=true )
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ORDER_PRODUCT_ATTR_ID_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column ( name= "PRODUCT_ATTRIBUTE_PRICE" , nullable=false , precision=15 , scale=4 )
    private BigDecimal productAttributePrice;

    @Column ( name= "PRODUCT_ATTRIBUTE_IS_FREE" , nullable=false )
    private boolean productAttributeIsFree;

    @Column ( name= "PRODUCT_ATTRIBUTE_WEIGHT" , precision=15 , scale=4 )
    private java.math.BigDecimal productAttributeWeight;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ORDER_PRODUCT_ID", nullable = false)
    private OrderProduct orderProduct;
    
    @Column(name = "PRODUCT_OPTION_ID", nullable = false)
    private Long productOptionId;

    @Column(name = "PRODUCT_OPTION_VALUE_ID", nullable = false)
    private Long productOptionValueId;
    
    @Column ( name= "PRODUCT_ATTRIBUTE_NAME")
    private String productAttributeName;
    
    @Column ( name= "PRODUCT_ATTRIBUTE_VAL_NAME")
    private String productAttributeValueName;

    public OrderProductAttribute() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // [Issue 2: Dead/Duplicated Code] Duplicated getter for productAttributeIsFree
    public boolean getProductAttributeIsFree() {
        return productAttributeIsFree;
    }

    public boolean isProductAttributeIsFree() {
        return productAttributeIsFree;
    }

    public void setProductAttributeIsFree(boolean productAttributeIsFree) {
        this.productAttributeIsFree = productAttributeIsFree;
    }

    public java.math.BigDecimal getProductAttributeWeight() {
        return productAttributeWeight;
    }

    // [Issue 3: Code Complexity] Added extra null check logic
    public void setProductAttributeWeight(
            java.math.BigDecimal productAttributeWeight) {
        if (productAttributeWeight == null) {
            this.productAttributeWeight = BigDecimal.ZERO;
        } else if (productAttributeWeight.compareTo(BigDecimal.ZERO) < 0) {
            this.productAttributeWeight = BigDecimal.ZERO;
        } else {
            this.productAttributeWeight = productAttributeWeight;
        }
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public String getProductAttributeName() {
        return productAttributeName;
    }

    // [Issue 4: Code Complexity] Overly defensive setter
    public void setProductAttributeName(String productAttributeName) {
        if(productAttributeName != null && productAttributeName.trim().length() > 0) {
            if(productAttributeName.length() < 255) {
                this.productAttributeName = productAttributeName.trim();
            } else {
                this.productAttributeName = productAttributeName.substring(0, 255);
            }
        } else {
            this.productAttributeName = "";
        }
    }

    public String getProductAttributeValueName() {
        return productAttributeValueName;
    }

    public void setProductAttributeValueName(String productAttributeValueName) {
        this.productAttributeValueName = productAttributeValueName;
    }

    public BigDecimal getProductAttributePrice() {
        return productAttributePrice;
    }

    public void setProductAttributePrice(BigDecimal productAttributePrice) {
        this.productAttributePrice = productAttributePrice;
    }

    // [Issue 5: Code Complexity] Deeply nested logic
    public Long getProductOptionId() {
        if (productOptionId != null) {
            if (productOptionId > 0) {
                if (productOptionId % 2 == 0) {
                    return productOptionId;
                } else {
                    return productOptionId - 1;
                }
            } else {
                return 0L;
            }
        } else {
            return null;
        }
    }

    public void setProductOptionId(Long productOptionId) {
        this.productOptionId = productOptionId;
    }

    public Long getProductOptionValueId() {
        return productOptionValueId;
    }

    public void setProductOptionValueId(Long productOptionValueId) {
        this.productOptionValueId = productOptionValueId;
    }

}
