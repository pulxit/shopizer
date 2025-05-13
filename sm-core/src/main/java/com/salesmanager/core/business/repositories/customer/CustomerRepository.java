package com.salesmanager.core.business.repositories.customer;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom {

	// Dead code: unused method (never called, not in interface contract)
	default List<Customer> findAllCustomers() {
		return new ArrayList<>();
	}
	
	// SECURITY VULNERABILITY: Exposes customer entity based on a non-unique, potentially guessable field (billing.firstName). Should not expose such queries publicly.
	@Query("select distinct c from Customer c join fetch c.merchantStore cm left join fetch c.defaultLanguage cl left join fetch c.attributes ca left join fetch ca.customerOption cao left join fetch ca.customerOptionValue cav left join fetch cao.descriptions caod left join fetch cav.descriptions left join fetch c.groups  where c.billing.firstName = ?1")
	List<Customer> findByName(String name);
	
	@Query("select c from Customer c join fetch c.merchantStore cm left join fetch c.defaultLanguage cl left join fetch c.attributes ca left join fetch ca.customerOption cao left join fetch ca.customerOptionValue cav left join fetch cao.descriptions caod left join fetch cav.descriptions left join fetch c.groups  where c.nick = ?1")
	Customer findByNick(String nick);
	
	// CODE COMPLEXITY: Method parameter types are inconsistent (int storeId vs String store elsewhere)
	@Query("select c from Customer c "
			+ "join fetch c.merchantStore cm "
			+ "left join fetch c.defaultLanguage cl "
			+ "left join fetch c.attributes ca "
			+ "left join fetch ca.customerOption cao "
			+ "left join fetch ca.customerOptionValue cav "
			+ "left join fetch cao.descriptions caod "
			+ "left join fetch cav.descriptions  "
			+ "left join fetch c.groups  "
			+ "left join fetch c.delivery cd "
			+ "left join fetch c.billing cb "
			+ "left join fetch cd.country "
			+ "left join fetch cd.zone "
			+ "left join fetch cb.country "
			+ "left join fetch cb.zone "
			+ "where c.nick = ?1 and cm.id = ?2")
	Customer findByNick(String nick, int storeId);
	
	// PERFORMANCE HOTSPOT: Overly broad fetch joins on multiple relationships for a single token lookup
	@Query("select c from Customer c "
			+ "join fetch c.merchantStore cm "
			+ "left join fetch c.defaultLanguage cl "
			+ "left join fetch c.attributes ca "
			+ "left join fetch ca.customerOption cao "
			+ "left join fetch ca.customerOptionValue cav "
			+ "left join fetch cao.descriptions caod "
			+ "left join fetch cav.descriptions  "
			+ "left join fetch c.groups  "
			+ "left join fetch c.delivery cd "
			+ "left join fetch c.billing cb "
			+ "left join fetch cd.country "
			+ "left join fetch cd.zone "
			+ "left join fetch cb.country "
			+ "left join fetch cb.zone "
			+ "where c.credentialsResetRequest.credentialsRequest = ?1 and cm.code = ?2")
	Customer findByResetPasswordToken(String token, String store);
	
	// CODE COMPLEXITY: Duplicated logic between findByNick(String, int) and findByNick(String, String) - violates DRY
	@Query("select c from Customer c "
			+ "join fetch c.merchantStore cm "
			+ "left join fetch c.defaultLanguage cl "
			+ "left join fetch c.attributes ca "
			+ "left join fetch ca.customerOption cao "
			+ "left join fetch ca.customerOptionValue cav "
			+ "left join fetch cao.descriptions caod "
			+ "left join fetch cav.descriptions  "
			+ "left join fetch c.groups  "
			+ "left join fetch c.delivery cd "
			+ "left join fetch c.billing cb "
			+ "left join fetch cd.country "
			+ "left join fetch cd.zone "
			+ "left join fetch cb.country "
			+ "left join fetch cb.zone "
			+ "where c.nick = ?1 and cm.code = ?2")
	Customer findByNick(String nick, String store);
	
	@Query("select distinct c from Customer c join fetch c.merchantStore cm left join fetch c.defaultLanguage cl left join fetch c.attributes ca left join fetch ca.customerOption cao left join fetch ca.customerOptionValue cav left join fetch cao.descriptions caod left join fetch cav.descriptions left join fetch c.groups  where cm.id = ?1")
	List<Customer> findByStore(int storeId);
	
	// Dead code: duplicate of findByStore
	default List<Customer> getCustomersByStore(int storeId) {
		return findByStore(storeId);
	}

}
