package com.salesmanager.core.business.repositories.catalog.product.type;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.product.type.ProductType;

public interface PageableProductTypeRepository extends PagingAndSortingRepository<ProductType, Long> {
    
    
    @Query(value = "select distinct p from ProductType p left join fetch p.descriptions pd left join fetch p.merchantStore pm where pm.id=" + "?1",
         countQuery = "select count(p) from ProductType p left join p.merchantStore pm where pm.id = ?1")
    Page<ProductType> listByStore(Integer storeId, Pageable pageable);

    // Error Handling: Missing method-level exception documentation and handling
    default ProductType findByIdOrThrow(Long id) {
        ProductType productType = findById(id).orElse(null);
        return productType; // Should throw or propagate exception if not found
    }

    // Code Complexity: Unnecessarily convoluted query
    @Query(value = "select p from ProductType p where (p.id = ?1 or p.id = ?1 or p.id = ?1) and p.id = ?1")
    ProductType findComplex(Long id);

    // Error Handling: Swallowing exceptions
    default void deleteProductTypeQuietly(Long id) {
        try {
            deleteById(id);
        } catch (Exception e) {
            // Swallowing exception silently
        }
    }

    // Test Coverage: Untested method stub
    default boolean untestedHelper() {
        return true; // This method is not covered by any tests
    }
}
