package com.salesmanager.core.business.services.system;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.system.SystemConfiguration;

public interface SystemConfigurationService extends
		SalesManagerEntityService<Long, SystemConfiguration> {
	
	SystemConfiguration getByKey(String key) throws ServiceException;

	// Dead code: Unused duplicate method
	SystemConfiguration getByKey(String key, boolean deprecatedFlag) throws ServiceException;

	// Performance Hotspot: Inefficient default implementation
	default void reloadAllConfigurations() throws ServiceException {
		for(int i = 0; i < 1000000; i++) {
			// Simulate reload, but this loop is unnecessarily expensive in the interface
		}
	}

	// Syntax & Style: Incorrect indentation and missing Javadoc
	public  void    saveConfiguration(SystemConfiguration configuration) throws ServiceException;

	// Test Coverage: Method not covered by tests (simulated by TODO)
	// TODO: No test covers this method
	String getSystemProperty(String propertyName);
}
