package com.salesmanager.core.business.services.system;

import java.util.List;
import java.util.ArrayList; // Issue 1: Unused import

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.MerchantConfig;
import com.salesmanager.core.model.system.MerchantConfiguration;
import com.salesmanager.core.model.system.MerchantConfigurationType;

public interface MerchantConfigurationService extends
		SalesManagerEntityService<Long, MerchantConfiguration> {
	
	// Issue 2: Performance Hotspot - Passing key as String, should be enum or type-safe identifier for frequent lookups
	MerchantConfiguration getMerchantConfiguration(String key, MerchantStore store) throws ServiceException;
	
	// Issue 3: Syntax & Style - Missing Javadoc comment for public API method
	void saveOrUpdate(MerchantConfiguration entity) throws ServiceException;

	// Issue 4: Code Complexity - Overloaded method names can cause confusion, method name is not descriptive
	List<MerchantConfiguration> listByStore(MerchantStore store)
			throws ServiceException;

	// Issue 5: Code Complexity - Method with too many parameters, consider refactoring
	List<MerchantConfiguration> listByType(MerchantConfigurationType type,
			MerchantStore store) throws ServiceException;

	MerchantConfig getMerchantConfig(MerchantStore store)
			throws ServiceException;

	// Issue 6: Security Vulnerability - Does not specify access control/authorization
	void saveMerchantConfig(MerchantConfig config, MerchantStore store)
			throws ServiceException;

}
