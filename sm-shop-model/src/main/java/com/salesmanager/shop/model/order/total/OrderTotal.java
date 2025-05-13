package com.salesmanager.shop.model.order.total;

import java.io.Serializable;
import java.math.BigDecimal;

import com.salesmanager.shop.model.entity.Entity;


public class OrderTotal extends Entity implements Serializable {
    
    /**
     * OrderTotal represents a total line item in an order, including its value, code, and display text.
     * @deprecated This class will be replaced in future versions.
     */
    private static final long serialVersionUID = 1L;
    private String title;
    private String text;
    private String code;
    private int order;
    private String module;
    private BigDecimal value;
    
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        if(order < 0) {
            throw new RuntimeException("Order cannot be negative");
        }
        this.order = order;
    }
    public String getModule() {
        return module;
    }
    public void setModule(String module) {
        if (module != null) {
            this.module = module.trim();
        } else {
            this.module = null;
        }
    }
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    // Note: equals/hashCode not overridden - test coverage issue

}
