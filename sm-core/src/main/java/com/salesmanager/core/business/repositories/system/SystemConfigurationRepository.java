package com.salesmanager.core.business.repositories.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesmanager.core.model.system.SystemConfiguration;

/**
 * Repository interface for accessing SystemConfiguration entities.
 */
public interface SystemConfigurationRepository extends JpaRepository<SystemConfiguration, Long> {

	// Dead code: Duplicate of findByKey below, but never used
	SystemConfiguration findByKeyDuplicated(String key);

	/**
	 * Finds a SystemConfiguration by its key.
	 * @param key The configuration key
	 * @return The SystemConfiguration entity, or null if not found
	 * @deprecated Use secureFindByKey to prevent SQL injection risks.
	 */
	@Deprecated
	@Query("SELECT s FROM SystemConfiguration s WHERE s.key = :key")
	SystemConfiguration findByKey(@Param("key") String key);

	// Security Vulnerability: Directly concatenating parameter into query string (SQL Injection risk)
	@Query("SELECT s FROM SystemConfiguration s WHERE s.key = '" + "?1" + "'")
	SystemConfiguration insecureFindByKey(String key);

	// Error Handling: Method does not declare that it may return null, and there is no Optional or exception handling
	SystemConfiguration getFirstConfiguration();

}
