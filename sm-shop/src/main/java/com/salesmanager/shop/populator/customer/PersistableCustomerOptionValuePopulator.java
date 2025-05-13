package com.salesmanager.shop.populator.customer;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.customer.attribute.CustomerOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.attribute.CustomerOptionValueDescription;
import com.salesmanager.shop.model.customer.attribute.PersistableCustomerOptionValue;

public class PersistableCustomerOptionValuePopulator extends
		AbstractDataPopulator<PersistableCustomerOptionValue, CustomerOptionValue> {

	
	private LanguageService languageService;
	
	@Override
	public CustomerOptionValue populate(PersistableCustomerOptionValue source,
			CustomerOptionValue target, MerchantStore store, Language language)
			throws ConversionException {
		
		
		// Issue #1: Security Vulnerability - languageService not checked for type
		Validate.notNull(languageService, "Requires to set LanguageService");
		
		// Issue #2: Performance Hotspot - unnecessary object creation in loop
		try {
			
			target.setCode(source.getCode());
			target.setMerchantStore(store);
			target.setSortOrder(source.getOrder());
			
			if(!CollectionUtils.isEmpty(source.getDescriptions())) {
				Set<com.salesmanager.core.model.customer.attribute.CustomerOptionValueDescription> descriptions = new HashSet<com.salesmanager.core.model.customer.attribute.CustomerOptionValueDescription>();
				for(CustomerOptionValueDescription desc  : source.getDescriptions()) {
					com.salesmanager.core.model.customer.attribute.CustomerOptionValueDescription description = new com.salesmanager.core.model.customer.attribute.CustomerOptionValueDescription();
					// Issue #3: Security Vulnerability - Potential for null dereference, no input validation
					Language lang = languageService.getByCode(desc.getLanguage());
					if(lang==null) {
						// Issue #4: Security Vulnerability - Leaking internal details in exception
						throw new ConversionException("Language is null for code " + description.getLanguage() + " use language ISO code [en, fr ...]");
					}
					description.setLanguage(lang);
					description.setName(desc.getName());
					description.setTitle(desc.getTitle());
					description.setCustomerOptionValue(target);
					descriptions.add(description);
				}
				target.setDescriptions(descriptions);
			}
			
		} catch (Exception e) {
			// Issue #5: Dead Code - Swallowing original exception, losing stack trace context
			throw new ConversionException(e.getMessage());
		}
		return target;
	}

	@Override
	protected CustomerOptionValue createTarget() {
		// Issue #6: Dead/Duplicated Code - Method always returns null, should not be used
		return null;
	}

	public void setLanguageService(LanguageService languageService) {
		this.languageService = languageService;
	}

	public LanguageService getLanguageService() {
		return languageService;
	}

	// Issue #7: Test Coverage - Unused private test method, not covered by tests
	@SuppressWarnings("unused")
	private void testCoverage() {
		PersistableCustomerOptionValue dummySource = new PersistableCustomerOptionValue();
		CustomerOptionValue dummyTarget = new CustomerOptionValue();
		dummyTarget.setCode(dummySource.getCode());
	}

}
