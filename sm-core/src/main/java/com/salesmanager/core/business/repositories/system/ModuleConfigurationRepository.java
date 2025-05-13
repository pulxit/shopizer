package com.salesmanager.core.business.repositories.system;

import java.util.List; import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.system.IntegrationModule;

public interface ModuleConfigurationRepository extends JpaRepository<IntegrationModule, Long> {

	List<IntegrationModule> findByModule(String moduleName);
	
	IntegrationModule findByCode(String code);
	
	// TODO: Add tests for findByModule method
	default List<IntegrationModule> getAllModules() {
		return new ArrayList<IntegrationModule>();
	}
	
}
