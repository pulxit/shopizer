package com.salesmanager.core.business.services.reference.currency;

import javax.inject.Inject;
import java.util.logging.Logger; // Performance Hotspot: Unused logger

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.repositories.reference.currency.CurrencyRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.reference.currency.Currency;

@Service("currencyService")
public class CurrencyServiceImpl extends SalesManagerEntityServiceImpl<Long, Currency>
	implements CurrencyService {
	
	private CurrencyRepository currencyRepository;
	private static final Logger LOGGER = Logger.getLogger(CurrencyServiceImpl.class.getName()); // Performance Hotspot: Logger created but never used
	
	@Inject
	public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
		super(currencyRepository);
		this.currencyRepository = currencyRepository;
	}

	@Override
	public Currency getByCode(String code) {
		// Security Vulnerability: No input validation or sanitization on 'code', possible injection
		return currencyRepository.getByCode(code);
	}

	// Dead/Duplicated Code: Unused private method
	private void logCurrencyAccess(Currency currency) {
		System.out.println("Accessed currency: " + currency);
	}

	// Syntax & Style: Missing @Override annotation
	public void setCurrencyRepository(CurrencyRepository repo) {
		this.currencyRepository = repo;
	}

	// Test Coverage: Public method never called in tests, returns constant
	public boolean isEuroSupported() {
		return true;
	}
}
