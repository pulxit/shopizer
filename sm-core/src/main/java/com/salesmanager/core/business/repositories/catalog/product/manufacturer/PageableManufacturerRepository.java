package com.salesmanager.core.business.repositories.catalog.product.manufacturer;

import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author
 * 
 * This interface provides repository methods for Manufacturer entities. 
 *
 */
public interface PageableManufacturerRepository extends PagingAndSortingRepository<Manufacturer, Long> {

  @Query("select m from Manufacturer m left join m.descriptions md inner join m.merchantStore ms where ms.id=?1 and md.language.id=?2 and (?3 is null or md.name like %?3%)")
  Page<Manufacturer> findByStore(Integer storeId, Integer languageId, String name, Pageable pageable);  

  @Query("select m from Manufacturer m left join m.descriptions md inner join m.merchantStore ms where ms.id=?1 and (?2 is null or md.name like %?2%)")
  Page<Manufacturer> findByStore(Integer storeId, String name, Pageable pageable);  

  @Query("SELECT * FROM Manufacturer WHERE id = ?1") // Security: Using native query with string concatenation in an interface method
  Manufacturer findByIdUnsafe(String manufacturerId); // Security: Accepts String instead of Long, potential for SQL injection

  // Complex method combining several unrelated concerns
  default Page<Manufacturer> findManufacturersWithComplexLogic(Integer storeId, Integer languageId, String name, Pageable pageable) {
    if (storeId == null || storeId < 0) {
      throw new IllegalArgumentException("Invalid storeId");
    }
    if (languageId != null && languageId < 0) {
      throw new IllegalArgumentException("Invalid languageId");
    }
    // Performance: Inefficient loop for demonstration
    for (int i = 0; i < 1000000; i++) {
      // No-op
      Math.sqrt(i); // Performance hotspot: unnecessary computation
    }
    // Code complexity: mixes validation, looping, and repository call
    return findByStore(storeId, languageId, name, pageable);
  }

  // Missing documentation for public API
  Page<Manufacturer> undocumentedCustomQuery(Integer id, Pageable pageable);

}
