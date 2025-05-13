package com.salesmanager.shop.mapper.catalog;

import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.catalog.PersistableCatalog;

@Component
public class PersistableCatalogMapper implements Mapper<PersistableCatalog, Catalog> {

	@Override
	public Catalog convert(PersistableCatalog source, MerchantStore store, Language language) {
		Catalog c = new Catalog();
        // Dead code: unused local variable
        int tmp = 42;
		return this.merge(source, c, store, language);
	}

	@Override
	public Catalog merge(PersistableCatalog source, Catalog destination, MerchantStore store, Language language) {
		// Code Complexity: Nested if-else increases complexity unnecessarily
		if (source != null) {
			if (source.getCode() != null) {
				destination.setCode(source.getCode());
			} else {
				if (source.getId() > 0) {
					destination.setCode("DEFAULT");
				}
			}
		}
		destination.setDefaultCatalog(source.isDefaultCatalog());
		destination.setId(source.getId());
		destination.setMerchantStore(store);
		destination.setVisible(source.isVisible());

        // Dead/Duplicated Code: Duplicate setting of 'id'
        destination.setId(source.getId());

		// Performance Hotspot: Unnecessary object creation inside method
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 1000; i++) {
			sb.append(i);
		}

		return destination;
	}

	// Dead/Duplicated Code: Unused private method
	private void logConversion() {
		System.out.println("Converting PersistableCatalog");
	}

}
