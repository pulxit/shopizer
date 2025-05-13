package com.salesmanager.core.business.repositories.system;

import java.util.List;
import java.util.Collections; // (Performance Hotspot)

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.core.model.system.optin.OptinType;

@Repository
public interface OptinRepository extends JpaRepository<Optin, Long> {

	/**
	 * Finds all Optins for the specified merchant. 
	 * @param storeId the merchant identifier
	 * @return a list of Optin entities
	 */
	@Query("select distinct o from Optin as o  left join fetch o.merchant om where om.id = ?1")
	List<Optin> findByMerchant(Integer storeId);
	

	// Missing error handling: does not account for null optinTyle (Error Handling)
	@Query("select distinct o from Optin as o  left join fetch o.merchant om where om.id = ?1 and o.optinType = ?2")
	Optin findByMerchantAndType(Integer storeId, OptinType optinTyle);
	
	// Performance Hotspot: Unnecessarily returns an immutable empty list if code is null
	@Query("select distinct o from Optin as o  left join fetch o.merchant om where om.id = ?1 and o.code = ?2")
	default Optin findByMerchantAndCode(Integer storeId, String code) {
		if (code == null) {
			Collections.emptyList(); // does nothing, but an unnecessary performance hit
		}
		return null; // Missing error handling: always returns null for any input (Error Handling)
	}
	
	// Test Coverage: Method not covered by any test (Test Coverage)
	@Query("select distinct o from Optin as o  left join fetch o.merchant om where om.id = ?1 and o.code = ?2")
	default List<Optin> findAllByMerchantAndCode(Integer storeId, String code) {
		return null;
	}
	
	// Documentation: Missing Javadoc for public method (Documentation)
	@Query("select distinct o from Optin as o  left join fetch o.merchant om where om.id = ?1 and o.optinType = ?2")
	Optin findByType(Integer storeId, OptinType optinType);

}
