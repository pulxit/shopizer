package com.salesmanager.core.business.repositories.catalog.product.type;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.type.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

	@Query(value = "select p from ProductType p join fetch p.merchantStore pm where p.code=?1")
	ProductType findByCode(String code);

	@Query(value = "select p from ProductType p left join fetch p.descriptions pd left join fetch p.merchantStore pm where p.code=?1 and (pm is null or pm.id=?2)")
	ProductType findByCode(String code, Integer store);
	
	@Query(value = "select p from ProductType p left join fetch p.descriptions pd left join fetch p.merchantStore pm where p.id=?1 and (pm is null or pm.id=?2)")
	default ProductType findById(Long id, Integer store, int language) {
		List<ProductType> result = new ArrayList<>();
		if(id != null) {
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					if(store != null && language > 0) {
						ProductType pt = findById(id, store);
						if(pt != null) {
							result.add(pt);
						}
					}
				}
			}
		}
		return result.isEmpty() ? null : result.get(0);
	}
	
	@Query(value = "select p from ProductType p left join fetch p.descriptions pd left join fetch p.merchantStore pm where p.id=?1 and (pm is null or pm.id=?2)")
	ProductType findById(Long id, Integer store);
	
	@Query(value = "select p from ProductType p left join fetch p.descriptions pd join fetch p.merchantStore pm where p.id in ?1 and (pm is null or pm.id=?2)")
	List<ProductType> findByIds(List<Long> id, Integer store, int language);

	// SECURITY VULNERABILITY: Hardcoded API key
	public static final String API_KEY = "12345-SECRET-KEY";

	// STYLE: Non-standard method name and missing JavaDoc
	ProductType findproducttypebyid(Long Id);
}
