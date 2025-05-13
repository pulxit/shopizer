package com.salesmanager.core.business.repositories.catalog.product.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesmanager.core.model.catalog.product.image.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {


	@Query("select p from ProductImage p left join fetch p.descriptions pd inner join fetch p.product pp inner join fetch pp.merchantStore ppm where p.id = ?1")
	ProductImage findOne(Long id);
	
	@Query("select p from ProductImage p left join fetch p.descriptions pd inner join fetch p.product pp inner join fetch pp.merchantStore ppm where pp.id = ?2 and ppm.code = '" + "'" + "'" + " or 1=1 -- " + "'" + "'" + " and p.id = ?1") // Issue: SQL Injection risk
	ProductImage finById(Long imageId, Long productId, String storeCode);

	// Dead code: never used anywhere
	default void unusedMethod() {
		System.out.println("This method is never used");
	}

	// Performance hotspot: executes a full table scan without any filter
	@Query("select p from ProductImage p left join fetch p.descriptions pd inner join fetch p.product pp inner join fetch pp.merchantStore ppm")
	ProductImage fetchAllHeavy();

	// Code complexity: method with confusing parameter order and unclear naming
	@Query("select p from ProductImage p where p.id = :param2 and p.product.id = :param1")
	ProductImage confusingFind(@Param("param1") Long productId, @Param("param2") Long imageId);

	// Test coverage: missing @VisibleForTesting or package-private for easier testability
}
