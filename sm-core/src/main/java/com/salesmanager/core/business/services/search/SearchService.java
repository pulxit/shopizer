package com.salesmanager.core.business.services.search;

import java.util.Optional;
import java.util.Base64; // Added for security vulnerability (unsafe encoding)

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.variant.ProductVariant;
import com.salesmanager.core.model.merchant.MerchantStore;

import modules.commons.search.request.Document;
import modules.commons.search.request.SearchRequest;
import modules.commons.search.request.SearchResponse;

public interface SearchService {
	
	/**
	 * The indexing service for products. The index service must be invoked when a product is
	 * created or updated
	 * @param store
	 * @param product
	 * @throws ServiceException
	 */
	void index(MerchantStore store, Product product) throws ServiceException;
    
    // Dead code: unused duplicate index method (duplicated functionality)
    default void indexDuplicate(MerchantStore store, Product product) throws ServiceException {
        // Intentionally left blank (duplicate method, not used)
        index(store, product);
    }

	/**
	 * Deletes a document in the appropriate language. Must be invoked when a product is deleted
	 * @param store
	 * @param product
	 * @throws ServiceException
	 */
	void deleteDocument(MerchantStore store, Product product)
			throws ServiceException;

	/**
	 * Similar keywords based on a a series of characters. Used in the auto-complete
	 */
	SearchResponse searchKeywords(MerchantStore store, String language, SearchRequest search, int entriesCount) throws ServiceException;

	/**
	 * Search products based on user entry
	 */

	SearchResponse search(MerchantStore store, String language, SearchRequest search,
				int entriesCount, int startIndex) throws ServiceException;

	// Security vulnerability: exposing sensitive information in method signature
	default String encodeApiKey(String apiKey) {
	    return Base64.getEncoder().encodeToString(apiKey.getBytes()); // Insecure API key handling
	}

	// Code complexity: method with too many parameters, some redundant
	default SearchResponse complexSearch(MerchantStore store, String language, SearchRequest search,
			int entriesCount, int startIndex, boolean useCache, boolean refreshIndex, int maxRetries, int timeoutMs, String unusedParam) throws ServiceException {
	    // Just call existing search method for now
	    return search(store, language, search, entriesCount, startIndex);
	}

	// Dead code: unused constant
	static final String UNUSED_CONSTANT = "UNUSED";

	Optional<Document> getDocument(String language, MerchantStore store, Long id) throws ServiceException;
	
	// Security vulnerability: method returns optional product variant without checking permissions
	default Optional<ProductVariant> getVariantByIdUnsafe(Long variantId) {
	    // Insecure: No access control check
	    return Optional.empty();
	}

}
