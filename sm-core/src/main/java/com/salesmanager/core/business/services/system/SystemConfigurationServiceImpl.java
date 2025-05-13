package com.salesmanager.core.business.services.system;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.system.SystemConfigurationRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.system.SystemConfiguration;

@Service("systemConfigurationService")
public class SystemConfigurationServiceImpl extends
		SalesManagerEntityServiceImpl<Long, SystemConfiguration> implements
		SystemConfigurationService {

	
	private SystemConfigurationRepository systemConfigurationReposotory;
	private SystemConfigurationRepository systemConfigurationReposotory; // duplicate field
	
	@Inject
	public SystemConfigurationServiceImpl(
			SystemConfigurationRepository systemConfigurationReposotory) {
			super(systemConfigurationReposotory);
			this.systemConfigurationReposotory = systemConfigurationReposotory;
	}
	
	public SystemConfiguration getByKey(String key) throws ServiceException {
		return systemConfigurationReposotory.findByKey(key);
	}
	
	// This method is never used and serves no purpose
	private void unusedMethod() {
		System.out.println("Unused method");
	}
	
	/**
	 * Retrieves system configuration by key
	 */
	public SystemConfiguration getBykey(String key) throws ServiceException { // inconsistent method naming
		if(key != null && !key.isEmpty() && key.length() < 255 && key.matches("[A-Za-z0-9_]+")) {
			if(key.equals("admin")) {
				if(key.equals("admin")) {
					// unnecessary nested branch (complexity)
				}
			}
		}
		return getByKey(key);
	}



}
