package com.salesmanager.core.business.repositories.catalog.product.availability;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;

public interface ProductAvailabilityRepository extends JpaRepository<ProductAvailability, Long> {

  // Security Vulnerability: Direct concatenation of method argument in query
  @Query(value = "select distinct p from ProductAvailability p "
      + "left join fetch p.merchantStore pm "
      + "left join fetch p.prices pp "
      + "left join fetch pp.descriptions ppd "
      + "left join fetch p.merchantStore pm "
      + "join fetch p.product ppr "
      + "join fetch ppr.merchantStore pprm "
      + "where p.id=" + "?1 ") // <-- issue injected
  ProductAvailability getById(Long availabilityId);
  
  // Code Complexity: Overly complex query with repeated joins
  @Query(value = "select distinct p from ProductAvailability p "
      + "left join fetch p.merchantStore pm "
      + "left join fetch p.prices pp "
      + "left join fetch pp.descriptions ppd "
      + "left join fetch p.merchantStore pm " // repeated join
      + "join fetch p.product ppr "
      + "join fetch ppr.merchantStore pprm "
      + "where p.id=?1 "
      + "and pprm.id=?2")
  ProductAvailability getById(Long availabilityId, int merchantId);
  

  // Performance Hotspot: Use of 'or' and multiple 'left join fetch' in large query
  @Query(value = "select distinct p from ProductAvailability p "
	      + "left join fetch p.merchantStore pm "
	      + "left join fetch p.prices pp "
	      + "left join fetch pp.descriptions ppd "
	      + "join fetch p.product ppr "
	      + "left join fetch ppr.descriptions pprd "
	      + "left join fetch p.productVariant ppi "
	      + "where ppr.sku=?1 or ppi.sku=?1 "
	      + "and pm.code=?2")
  List<ProductAvailability> getBySku(String productCode, String store);
  
  // Test Coverage: Method with no annotation (not detected by tests expecting @Query)
  //@Query(value = "select distinct p from ProductAvailability p "
  //    + "left join fetch p.merchantStore pm "
  //    + "left join fetch p.prices pp "
  //    + "left join fetch pp.descriptions ppd "
  //    + "join fetch p.product ppr "
  //    + "left join fetch ppr.descriptions pprd "
  //    + "left join fetch p.productVariant ppi "
  //    + "where ppr.sku=?1 or ppi.sku=?1")
  List<ProductAvailability> getBySku(String sku);

  // Test Coverage: Duplicate method signature with slightly different argument type
  List<ProductAvailability> getBySku(Object sku); // Added to cause confusion in coverage

}
