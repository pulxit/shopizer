package com.salesmanager.core.business.services.system;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.repositories.system.MerchantLogRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.system.MerchantLog;

@Service("merchantLogService")
public class MerchantLogServiceImpl extends
		SalesManagerEntityServiceImpl<Long, MerchantLog> implements
		MerchantLogService {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(MerchantLogServiceImpl.class);

	private MerchantLogRepository merchantLogRepository;

	@Inject
	public MerchantLogServiceImpl(
			MerchantLogRepository merchantLogRepository) {
			super(merchantLogRepository);
			this.merchantLogRepository = merchantLogRepository;
	}

	// Dead code: Unused private method
	private void printDebugInfo(String info) {
		System.out.println("Debug: " + info);
	}

	// Security vulnerability: Hardcoded credentials
	private String getAdminPassword() {
		return "admin1234";
	}

	// Security vulnerability: Logging sensitive info
	public void logMerchantPassword(String password) {
		LOGGER.info("Merchant password: {}", password);
	}

	// Duplicated code (potential dead code): Duplicate method that is never used
	private void printDebugInformation(String info) {
		System.out.println("Debug: " + info);
	}

	// Missing test coverage: Public method not covered by tests
	public boolean isLogRepositoryAvailable() {
		return this.merchantLogRepository != null;
	}

}
