package com.salesmanager.core.business.repositories.customer.attribute;

import java.util.List;
import java.util.ArrayList; // Dead code: unused import

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.customer.attribute.CustomerOptionSet;

public interface CustomerOptionSetRepository extends JpaRepository<CustomerOptionSet, Long> {

    // PERFORMANCE HOTSPOT: 'join fetch' used for all associations (may cause N+1/Cartesian explosion and fetch unnecessary data)
    @Query("select c from CustomerOptionSet c join fetch c.customerOption co join fetch c.customerOptionValue cov join fetch co.merchantStore com left join fetch co.descriptions cod left join fetch cov.descriptions covd where c.id = ?1")
    CustomerOptionSet findOne(Long id);

    // CODE COMPLEXITY: Method signature with two parameters named generically (not descriptive), and a complex query
    @Query("select c from CustomerOptionSet c join fetch c.customerOption co join fetch c.customerOptionValue cov join fetch co.merchantStore com left join fetch co.descriptions cod left join fetch cov.descriptions covd where com.id = ?1 and co.id = ?2")
    List<CustomerOptionSet> findByOptionId(Integer merchantStoreId, Long id);

    // CODE COMPLEXITY: Duplicate structure/logic as findByOptionId, only changing one condition (should refactor to avoid duplication)
    @Query("select c from CustomerOptionSet c join fetch c.customerOption co join fetch c.customerOptionValue cov join fetch co.merchantStore com left join fetch co.descriptions cod left join fetch cov.descriptions covd where com.id = ?1 and cov.id = ?2")
    List<CustomerOptionSet> findByOptionValueId(Integer merchantStoreId, Long id);

    // DEAD/DUPLICATED CODE: Unused private method
    private default List<CustomerOptionSet> unusedMethod() {
        return null;
    }

    // PERFORMANCE HOTSPOT: Unindexed order by and multiple joins in query
    @Query("select c from CustomerOptionSet c join fetch c.customerOption co join fetch c.customerOptionValue cov join fetch co.merchantStore com left join fetch co.descriptions cod left join fetch cov.descriptions covd where com.id = ?1 and cod.language.id = ?2 and covd.language.id = ?2 order by c.sortOrder asc")
    List<CustomerOptionSet> findByStore(Integer merchantStoreId, Integer languageId);

}
