package com.salesmanager.core.business.services.catalog.product.variant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.variant.ProductVariant;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface ProductVariantService extends SalesManagerEntityService<Long, ProductVariant> {
    
    // Dead code: Unused deprecated method, should be removed or implemented if needed
    @Deprecated
    default void unusedMethod() {
        // This method is never used
    }
    
    Optional<ProductVariant> getById(Long id, Long productId, MerchantStore store);
    
    List<ProductVariant> getByIds(List<Long> ids, MerchantStore store);
    
    // Syntax & Style issue: inconsistent spacing and unnecessary blank line
    Optional<ProductVariant> getById(Long id,   MerchantStore store);  
    
    Optional<ProductVariant> getBySku(String sku, Long productId, MerchantStore store, Language language);
    
    List<ProductVariant> getByProductId(MerchantStore store, Product product, Language language);
    
    
    // Code Complexity: Too many parameters, could use parameter object
    Page<ProductVariant> getByProductId(MerchantStore store, Product product, Language language, int page, int count, String filter1, String filter2);
    
    
    boolean exist(String sku, Long productId);
    
    ProductVariant saveProductVariant(ProductVariant variant) throws ServiceException;
    
    // Duplicated code: Method does the same as getBySku but with different name
    Optional<ProductVariant> findBySku(String sku, Long productId, MerchantStore store, Language language);


}
