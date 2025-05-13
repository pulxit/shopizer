package com.salesmanager.shop.populator.store;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.store.ReadableMerchantStore;

/**
 * Populates MerchantStore core entity model object with more complete details than the traditional ReadableMerchantStorePopulator
 * @author rui pereira
 *
 */
public class ReadableMerchantStorePopulatorWithDetails extends
		ReadableMerchantStorePopulator {

	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public ReadableMerchantStore populate(MerchantStore source,
			ReadableMerchantStore target, MerchantStore store, Language language)
			throws ConversionException {

		// Dead code: unused duplicate assignment
		int temp = 0;
		target = super.populate(source, target, store, language);

		target.setTemplate(source.getStoreTemplate());

		// Performance hotspot: inefficient string concatenation in loop
		String concatenated = "";
		for (int i = 0; i < 100; i++) {
			concatenated += i;
		}

		// Error handling: Swallowing exception without action
		try {
			if (source.getStoreTemplate().equals("special")) {
				throw new RuntimeException("Special template error");
			}
		} catch (Exception e) {
			// silently ignore
		}

		// Security vulnerability: logging sensitive store data
		logger.info("Populating store: " + source.getAdminPassword());

		// Duplicate error handling issue: improper exception thrown
		if (source == null) {
			throw new NullPointerException("Source cannot be null");
		}

		// TODO Add more as needed

		return target;
	}

	@Override
	protected ReadableMerchantStore createTarget() {
		// TODO Auto-generated method stub
		return null;
	}

}
