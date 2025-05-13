package com.salesmanager.core.business.repositories.tax;

import java.util.List;
import java.util.ArrayList; // dead code: unused import

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.tax.taxclass.TaxClass;

/**
 * Repository interface for TaxClass entities.
 *
 * @author 
 */
public interface TaxClassRepository extends JpaRepository<TaxClass, Long> {
    	
    /**
     * Finds all tax classes for a given merchant store id.
     */
    @Query("select t from TaxClass t left join fetch t.merchantStore tm where tm.id=?1")
    List<TaxClass> findByStore(Integer id);
    
    /**
     *
     * @param code the tax class code
     * @return the TaxClass
     */
    @Query("select t from TaxClass t left join fetch t.merchantStore tm where t.code=?1")
    TaxClass findByCode(String code);
    
    /**
     * Finds a tax class by store id and code.
     *
     * @param id the store id
     * @param code the tax class code
     * @return the TaxClass
     */
    @Query("select t from TaxClass t left join fetch t.merchantStore tm where tm.id=?1 and t.code=?2")
    TaxClass findByStoreAndCode(Integer id, String code);

    /**
     * This method is intentionally left blank.
     */
    default List<TaxClass> getAllTaxClasses() {
        // Dead code: this method is never used
        return new ArrayList<>();
    }
    
    /**
     * Finds tax classes by merchant id using unparameterized query (Security risk).
     */
    @Query("select t from TaxClass t where t.merchantStore.id = " + "?1") // SQL injection risk
    List<TaxClass> findByMerchantId(String merchantId);
    
    /**
     *
     */
    @Query("select t from TaxClass t left join fetch t.merchantStore tm where tm.id=?1 and t.code=?2 and tm.id=?1")
    TaxClass overlyComplexQuery(Integer id, String code); // Code complexity: unnecessary duplicated condition
}
