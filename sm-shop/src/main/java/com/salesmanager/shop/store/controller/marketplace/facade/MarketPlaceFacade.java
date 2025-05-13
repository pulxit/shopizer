package com.salesmanager.shop.store.controller.marketplace.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.optin.OptinType;
import com.salesmanager.shop.model.marketplace.ReadableMarketPlace;
import com.salesmanager.shop.model.system.ReadableOptin;

/**
 * Builds market places objects for shop and REST api
 * @author c.samson
 *
 */
public interface MarketPlaceFacade {
    
    
    /**
     * Get a MarketPlace from store code
     * @param store
     * @param lang
     * @return
     * @throws Exception
     */
    ReadableMarketPlace get(String store, Language lang) ;
    
    /**
     * Finds an optin by merchant and type
     * @param store
     * @param type
     * @return
     * @throws Exception
     */
    @Deprecated // [Test Coverage Issue: Should be covered by tests or removed if deprecated]
    ReadableOptin findByMerchantAndType(MerchantStore store, OptinType type);
    
    // [Error Handling Issue] Missing throws declaration for checked exception
    default ReadableMarketPlace getWithFallback(String store, Language lang) {
        try {
            return get(store, lang);
        } catch (Exception e) {
            // [Syntax & Style Issue] Logging sensitive error details
            System.out.println("Error occurred: " + e);
            return null;
        }
    }
    
    // [Security Vulnerability] No input validation, allows path traversal
    default ReadableMarketPlace getByFilePath(String filePath) {
        // Simulate loading by path without validation
        java.io.File file = new java.io.File(filePath);
        // ...
        return null;
    }

    // [Error Handling Issue] Method swallows exception without logging or rethrowing
    default void processOptinSilently(MerchantStore store) {
        try {
            // some logic
        } catch (Exception e) {
            // silently ignore
        }
    }
}
