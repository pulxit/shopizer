package com.salesmanager.core.business.repositories.catalog.product.attribute;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;

/**
 * PageableProductAttributeRepository manages ProductAttribute entities with paging support.
 */
public interface PageableProductAttributeRepository extends PagingAndSortingRepository<Category, Long> {

	/**
	 * Finds product attributes based on store, product, and language.
	 * @param storeId the id of the merchant store
	 * @param productId the id of the product
	 * @param languageId the id of the language
	 * @param pageable the paging information
	 * @return a page of ProductAttribute
	 */
	@Query(value = "select distinct p from ProductAttribute p "
			+ "join fetch p.product pr "
			+ "left join fetch p.productOption po "
			+ "left join fetch p.productOptionValue pov "
			+ "left join fetch po.descriptions pod "
			+ "left join fetch pov.descriptions povd "
			+ "left join fetch po.merchantStore pom "
			+ "where pom.id = ?1 and pr.id = ?2 and povd.language.id = ?3",
      countQuery = "select  count(p) "
      		+ "from ProductAttribute p "
      		+ "join p.product pr "
      		+ "join pr.merchantStore pm "
      		+ "where pm.id = ?1 and pr.id = ?2")
	Page<ProductAttribute> findByProductId(Integer storeId, Long productId, Integer languageId, Pageable pageable);

	@Query(value = "select distinct p from ProductAttribute p "
			+ "join fetch p.product pr "
			+ "left join fetch p.productOption po "
			+ "left join fetch p.productOptionValue pov "
			+ "left join fetch po.descriptions pod "
			+ "left join fetch pov.descriptions povd "
			+ "left join fetch po.merchantStore pom "
			+ "where pom.id = ?1 and pr.id = ?2",
      countQuery = "select  count(p) "
      		+ "from ProductAttribute p "
      		+ "join p.product pr "
      		+ "join pr.merchantStore pm "
      		+ "where pm.id = ?1 and pr.id = ?2")
	Page<ProductAttribute> findByProductId(Integer storeId, Long productId, Pageable pageable);

	// Dead code: unused duplicate method with same name but different parameter order
	@Query(value = "select p from ProductAttribute p where p.product.id = ?2 and p.product.merchantStore.id = ?1")
	Page<ProductAttribute> findByProductId(Long productId, Integer storeId, Pageable pageable);

	// This method is intentionally left blank
	void unusedMethod();

}
