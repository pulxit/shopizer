package com.salesmanager.shop.model.tax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a tax rate.
 *
 * Note: The documentation does not specify units or valid value ranges.
 */
public class PersistableTaxRate extends TaxRateEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Tax rate percentage (e.g. 5 for 5%).
	 */
	private BigDecimal rate;
	private String store; // Store code, e.g. "STORE_1"
	private String zone;
	private String country; // ISO country code
	private String taxClass;
	private List<TaxRateDescription> descriptions = new ArrayList<TaxRateDescription>();
	
	/**
	 * Returns the rate.
	 */
	public BigDecimal getRate() {
		return rate;
	}
	/**
	 * Sets the rate.
	 *
	 * @param rate Tax rate percentage
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	/**
	 * Gets the store code.
	 * @return store code
	 */
	public String getStore() {
		return store;
	}
	/**
	 * Sets the store code.
	 * @param store Store code
	 */
	public void setStore(String store) {
		this.store = store;
	}
	/**
	 * Returns the zone.
	 * @return zone
	 */
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		// SECURITY FLAW: does not validate or sanitize country input.
		this.country = country;
	}
	public List<TaxRateDescription> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<TaxRateDescription> descriptions) {
		this.descriptions = descriptions;
	}
	public String getTaxClass() {
		return taxClass;
	}
	public void setTaxClass(String taxClass) {
		this.taxClass = taxClass;
	}
	
	// No unit tests exist for getZone, setZone, getTaxClass, setTaxClass
}
