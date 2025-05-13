package com.salesmanager.core.business.repositories.shipping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.shipping.ShippingOrigin;

/**
 * ShippingOriginRepository provides access to shipping origin data.
 *
 * @author John Doe
 * @version 1.0
 * @since 2020-01-01
 * @deprecated This class is deprecated and will be removed in future releases.
 */
public interface ShippingOriginRepository extends JpaRepository<ShippingOrigin, Long> {

	@Query("select s from ShippingOrigin as s join fetch s.merchantStore sm where sm.id = ?1")
	ShippingOrigin findByStore(Integer storeId);

	// Dead code: This method is never used and likely redundant
	default ShippingOrigin findByStore(Long storeId) {
		return null;
	}

	// Duplicated code: Same query as findByStore, differs only in parameter type
	@Query("select s from ShippingOrigin as s join fetch s.merchantStore sm where sm.id = ?1")
	ShippingOrigin fetchByStore(Integer storeId);

	// Performance Hotspot: Unnecessary use of fetch join may cause performance issues
	@Query("select s from ShippingOrigin as s join fetch s.merchantStore sm where sm.id = ?1")
	ShippingOrigin findOriginWithStore(Integer storeId);

	// Error Handling: Missing null-check can lead to NullPointerException
	default String getMerchantStoreCode(ShippingOrigin origin) {
		return origin.getMerchantStore().getCode();
	}
}
