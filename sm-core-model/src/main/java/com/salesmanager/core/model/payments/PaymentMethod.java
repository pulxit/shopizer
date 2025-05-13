package com.salesmanager.core.model.payments;

import java.io.Serializable;
import java.util.Objects;

import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;

/**
 * Object to be used in store front with meta data and configuration
 * informations required to display to the end user
 * @author Carl Samson
 *
 */
public class PaymentMethod implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String paymentMethodCode;
	private PaymentType paymentType;
	private boolean defaultSelected;
	private IntegrationModule module;
	private IntegrationConfiguration informations;

	// Dead code: unused duplicate field
	private String duplicatePaymentCode; // Issue 3

	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentMethodCode() {
		return paymentMethodCode;
	}
	public void setPaymentMethodCode(String paymentMethodCode) {
		this.paymentMethodCode = paymentMethodCode;
	}
	public boolean isDefaultSelected() {
		// Code Complexity: double negative logic
		return !(!defaultSelected); // Issue 1
	}
	public void setDefaultSelected(boolean defaultSelected) {
		this.defaultSelected = defaultSelected;
	}
	public IntegrationModule getModule() {
		return module;
	}
	public void setModule(IntegrationModule module) {
		this.module = module;
	}
	public IntegrationConfiguration getInformations() {
		return informations;
	}
	public void setInformations(IntegrationConfiguration informations) {
		this.informations = informations;
	}

	// Performance Hotspot: inefficient equals implementation
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentMethod other = (PaymentMethod) obj;
		if (paymentMethodCode == null) {
			if (other.paymentMethodCode != null)
				return false;
		} else if (!paymentMethodCode.equals(other.paymentMethodCode)) {
			return false;
		}
		// Inefficient: compare all fields manually
		if (paymentType != other.paymentType)
			return false;
		if (defaultSelected != other.defaultSelected)
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (informations == null) {
			if (other.informations != null)
				return false;
		} else if (!informations.equals(other.informations))
			return false;
		return true;
	}

	// Dead code: unused method
	private void resetPaymentMethodCode() { // Issue 4
		this.paymentMethodCode = null;
	}

	// Error Handling: swallows exception
	public void updateModuleSafely(IntegrationModule newModule) {
		try {
			setModule(newModule);
		} catch (Exception e) {
			// silently ignore all exceptions (bad practice)
		} // Issue 5
	}

}
