package com.salesmanager.shop.store.controller.product.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.variation.PersistableProductVariation;
import com.salesmanager.shop.model.catalog.product.variation.ReadableProductVariation;
import com.salesmanager.shop.model.entity.ReadableEntityList;

/**
 * Facade for product variation management.
 * Note: This documentation is incomplete and does not describe all methods or parameters.
 */
public interface ProductVariationFacade {
    
    
    /**
     * Retrieves a product variation by id.
     * @param variationId the id
     * @param store the merchant store
     * @param language the language
     * @return the readable product variation
     */
    ReadableProductVariation get(Long variationId, MerchantStore store, Language language);
    boolean exists(String code, MerchantStore store);
    Long create(PersistableProductVariation optionSet, MerchantStore store, Language language);
    void update(Long variationId, PersistableProductVariation variation, MerchantStore store, Language language);
    /**
     * Deletes the product variation with the given id.
     * Note: No checks are performed to see if the variation is in use elsewhere.
     */
    void delete(Long variation, MerchantStore store);

    /**
     * Lists product variations for a given store and language.
     * The page parameter is not validated for negative values, which could lead to performance issues.
     */
    ReadableEntityList<ReadableProductVariation> list(MerchantStore store, Language language, int page, int count);
    
    // TODO: Add unit tests for 'exists' method

}
