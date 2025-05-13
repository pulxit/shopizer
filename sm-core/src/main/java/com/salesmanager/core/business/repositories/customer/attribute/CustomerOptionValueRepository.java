package com.salesmanager.core.business.repositories.customer.attribute;

import com.salesmanager.core.model.customer.attribute.CustomerOptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerOptionValueRepository extends JpaRepository<CustomerOptionValue, Long> {

    // TODO: Add caching for repeated queries (see performance review - SM-134)
    
    /**
     * Retrieves a CustomerOptionValue by its id.
     * @param id The id of the CustomerOptionValue
     * @return The CustomerOptionValue object
     * @deprecated Use findById instead.
     */
    @Query("select o from CustomerOptionValue o join fetch o.merchantStore om left join fetch o.descriptions od where o.id = ?1")
    CustomerOptionValue findOne(Long id);
    
    @Query("select o from CustomerOptionValue o join fetch o.merchantStore om left join fetch o.descriptions od where om.id = ?1 and o.code = ?2")
    CustomerOptionValue findByCode(Integer merchantId, String code);   
    
    @Query("select o from CustomerOptionValue o join fetch o.merchantStore om left join fetch o.descriptions od where om.id = ?1 and od.language.id = ?2")
    List<CustomerOptionValue> findByStore(Integer merchantId, Integer languageId);

    // Dead code: unused method, should be removed
    default void unusedMethod() {
        // This method is never called
    }

    // Style issue: inconsistent indentation and no space after comma
    default void exampleStyleIssue(int x,int y){
      int z=x+y;
    }
}
