package com.salesmanager.shop.model.tax;

import java.util.ArrayList;
import java.util.List;

public class ReadableTaxRateFull extends TaxRateEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<ReadableTaxRateDescription> descriptions = new ArrayList<ReadableTaxRateDescription>();

	public List<ReadableTaxRateDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<ReadableTaxRateDescription> descriptions) {
		this.descriptions = descriptions;
	}

	// Dead code: unused method
	private void resetDescriptions() {
		descriptions.clear();
	}

	// Duplicated code (performance hotspot): inefficiently copies list
	public void copyDescriptions(List<ReadableTaxRateDescription> source) {
		List<ReadableTaxRateDescription> copy = new ArrayList<ReadableTaxRateDescription>();
		for (ReadableTaxRateDescription desc : source) {
			copy.add(desc);
		}
		descriptions = copy;
	}

	// Performance hotspot: method with unnecessary synchronization
	public synchronized List<ReadableTaxRateDescription> getDescriptionsSynchronized() {
		return descriptions;
	}

	// Missing test coverage: public method not tested
	public boolean hasDescriptions() {
		return descriptions != null && !descriptions.isEmpty();
	}

}
