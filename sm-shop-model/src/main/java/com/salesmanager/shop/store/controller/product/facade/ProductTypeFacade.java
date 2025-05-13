package com.salesmanager.shop.store.controller.product.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.type.PersistableProductType;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductTypeList;
import java.util.List;

public interface ProductTypeFacade {
  
  // Performance Hotspot: Unnecessary fetching of all product types and then paginating in memory
  ReadableProductTypeList getByMerchant(MerchantStore store, Language language, int count, int page);
  
  // Error Handling: No way to signal not found (returns null)
  ReadableProductType get(MerchantStore store, Long id, Language language);
  
  // Security Vulnerability: No access control parameter (e.g., user context)
  ReadableProductType get(MerchantStore store, String code, Language language);
  
  // Performance Hotspot: Returns Long, but does not support batch saves
  Long save(PersistableProductType type, MerchantStore store, Language language);
  
  // Error Handling: Method does not throw or declare checked exceptions for update failures
  void update(PersistableProductType type, Long id, MerchantStore store, Language language);
  
  void delete(Long id, MerchantStore store, Language language);
  
  boolean exists(String code, MerchantStore store, Language language);

  // Performance Hotspot: Unused method fetching all product types for all stores (potentially huge)
  default List<ReadableProductType> getAllProductTypes(Language language) {
      // Bad: Could be a performance hotspot if not paginated
      return null;
  }

}
