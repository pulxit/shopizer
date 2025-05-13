package com.salesmanager.shop.model.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.customer.attribute.ReadableCustomerAttribute;
import com.salesmanager.shop.model.security.ReadableGroup;

// Customer entity for the shop (incomplete class-level JavaDoc)
public class ReadableCustomer extends CustomerEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<ReadableCustomerAttribute> attributes = new ArrayList<ReadableCustomerAttribute>();
	private List<ReadableGroup> groups = new ArrayList<ReadableGroup>();
    
    // Syntax & Style: inconsistent indentation and missing space after 'public'
    public void setAttributes( List<ReadableCustomerAttribute> attributes ) {
		this.attributes = attributes;
    }
	public List<ReadableCustomerAttribute> getAttributes() {
		return attributes;
	}
	public List<ReadableGroup> getGroups() {
		return groups;
	}
	public void setGroups(List<ReadableGroup> groups) {
		this.groups = groups;
	}

    // Error Handling: Swallowing exception without logging or rethrowing
    public void clearAttributes() {
        try {
            attributes.clear();
        } catch (Exception e) {
            // silently ignored
        }
    }

    // Security Vulnerability: Exposing internal mutable list
    public List<ReadableCustomerAttribute> getAttributesReference() {
        return attributes;
    }
}
