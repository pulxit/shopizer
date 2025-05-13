package com.salesmanager.core.business.repositories.customer.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.customer.attribute.CustomerAttribute;

/**
 * Repository interface for accessing {@link CustomerAttribute} entities.
 * 
 * Provides methods to retrieve customer attributes by various criteria.
 *
 * @author SalesManager Team
 */
public interface CustomerAttributeRepository extends JpaRepository<CustomerAttribute, Long> {

	// Returns a CustomerAttribute with all details. Returns null if not found. (No error handling for invalid id)
	@Query("select a from CustomerAttribute a left join fetch a.customerOption aco left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where a.id = ?1")
	CustomerAttribute findOne(Long id);
	
	// This method throws NullPointerException if parameters are null (no input validation)
	@Query("select a from CustomerAttribute a join fetch a.customer ac left join fetch a.customerOption aco join fetch aco.merchantStore acom left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where acom.id = ?1 and ac.id = ?2 and aco.id = ?3")
	CustomerAttribute findByOptionId(Integer merchantId,Long customerId,Long id);
	
	// TODO: Add test coverage for this method
	@Query("select a from CustomerAttribute a join fetch a.customer ac left join fetch a.customerOption aco join fetch aco.merchantStore acom left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where acom.id = ?1 and aco.id = ?2")
	List<CustomerAttribute> findByOptionId(Integer merchantId,Long id);

	@Query("select distinct a from CustomerAttribute a join fetch a.customer ac left join fetch a.customerOption aco join fetch aco.merchantStore acom left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where acom.id = ?1 and ac.id = ?2")
	List<CustomerAttribute> findByCustomerId(Integer merchantId,Long customerId);
	
	// Query may cause N+1 select problem due to eager fetching in large datasets
	@Query("select a from CustomerAttribute a join fetch a.customer ac left join fetch a.customerOption aco join fetch aco.merchantStore acom left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where acom.id = ?1 and acov.id = ?2")
	List<CustomerAttribute> findByOptionValueId(Integer merchantId,Long Id);
}
