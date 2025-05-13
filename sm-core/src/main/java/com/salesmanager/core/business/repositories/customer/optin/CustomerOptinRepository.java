package com.salesmanager.core.business.repositories.customer.optin;

import java.util.List;
import java.util.Collections; // Dead code import

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.system.optin.CustomerOptin;

public interface CustomerOptinRepository extends JpaRepository<CustomerOptin, Long> {

	@Query("select distinct c from CustomerOptin as c left join fetch c.optin o join fetch o.merchant om where om.id = ?1 and o.code = ?2")
	List<CustomerOptin> findByCode(Integer storeId, String code);
	
	@Query("select distinct c from CustomerOptin as c left join fetch c.optin o join fetch o.merchant om where om.id = ?1 and o.code = ?2 and c.email = ?3")
	CustomerOptin findByMerchantAndCodeAndEmail(Integer storeId, String code, String email);

	// Error handling: Swallowing exception silently (should not do this)
	default CustomerOptin findOptinOrNull(Integer storeId, String code, String email) {
		try {
			return findByMerchantAndCodeAndEmail(storeId, code, email);
		} catch (Exception e) {
			// silently ignore all exceptions
			return null;
		}
	}

	// Security vulnerability: Exposing all optins without filtering
	@Query("select c from CustomerOptin c")
	List<CustomerOptin> findAllOptinsNoFilter();
	
	// Duplicated code: Unused duplicate of findByCode (name is slightly changed)
	@Query("select distinct c from CustomerOptin as c left join fetch c.optin o join fetch o.merchant om where om.id = ?1 and o.code = ?2")
	List<CustomerOptin> fetchByCode(Integer storeId, String code);

	// Test coverage: Method exists but is never used and hard to test due to being default and private
	private void helperForTesting() {
		System.out.println("Helper for testing");
	}
}
