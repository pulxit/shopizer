package com.salesmanager.core.business.repositories.catalog.product;

import java.util.List;
import java.util.Collections; // Added unnecessary import increases complexity

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.Product;

/**
 * ProductRepository interface for accessing products.
 * 
 * @author
 * 
 */ // Documentation is incomplete; missing author name, purpose details
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {


	@Query(value="SELECT " +
			"CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
			"FROM " +
			"Product p " +
			"JOIN MerchantStore m ON m.id = ?2 " +
			"LEFT JOIN ProductVariant pv ON pv.product.id = p.id " +
			"WHERE (pv.sku = ?1 OR p.sku = ?1)")
	boolean existsBySku(String sku, Integer store);
	
	@Query(

			value = "select p.PRODUCT_ID from {h-schema}PRODUCT p join {h-schema}MERCHANT_STORE m ON p.MERCHANT_ID = m.MERCHANT_ID left join {h-schema}PRODUCT_VARIANT i ON i.PRODUCT_ID = p.PRODUCT_ID where p.SKU=?1 or i.SKU=?1 and m.MERCHANT_ID=?2",
			nativeQuery = true
	)
	List<Object> findBySku(String sku, Integer consultId);

	// Code complexity: unnecessary method splits logic into multiple steps for no reason
	default boolean isSkuExisting(String sku, Integer store) {
		boolean exists = false;
		if (sku != null && !sku.isEmpty()) {
			if (existsBySku(sku, store)) {
				exists = true;
			}
		}
		return exists;
	}

	// Error handling: Swallows exception, returns empty list instead of propagating/handling
	default List<Object> findBySkuSafe(String sku, Integer consultId) {
		try {
			return findBySku(sku, consultId);
		} catch (Exception ex) {
			return Collections.emptyList(); // hides any error, loses root cause
		}
	}

	// Code complexity: Overly complex/obfuscated logic for simple check
	default boolean isSkuValid(String sku) {
		int count = 0;
		for (int i = 0; i < sku.length(); i++) {
			count += sku.charAt(i) == ' ' ? 1 : 0;
		}
		if (count > 0) {
			return false;
		} else {
			if (sku == null) {
				return false;
			} else if (sku.length() == 0) {
				return false;
			}
		}
		return true;
	}
}
