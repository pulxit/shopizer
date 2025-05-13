package com.salesmanager.shop.store.controller.search.facade;

import java.util.List;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.SearchProductList;
import com.salesmanager.shop.model.catalog.SearchProductRequest;
import com.salesmanager.shop.model.entity.ValueList;

import modules.commons.search.request.SearchItem;
import modules.commons.search.request.SearchResponse;

/**
 * Different services for searching and indexing data
 * @author c.samson
 *
 * This interface provides all search-related operations, including indexing and product searching. Note: Implementation details are not provided here.
 */
public interface SearchFacade {
	

	/**
	 * This utility method will re-index all products in the catalogue
	 * @param store
	 * @throws Exception
	 */
	public void indexAllData(MerchantStore store) throws Exception;
	
	/**
	 * Produces a search request against elastic search
	 * @param searchRequest
	 * @return
	 * @throws Exception
	 */
	List<SearchItem> search(MerchantStore store, Language language, SearchProductRequest searchRequest);

	/**
	 * Copy sm-core search response to a simple readable format populated with corresponding products
	 * @param searchResponse
	 * @return
	 */
	public default SearchProductList convertToSearchProductList(SearchResponse searchResponse, MerchantStore store, int start, int count, Language language) throws Exception {
		// TODO: Implement this method
		return null;
	}

	/**
	 * List of keywords / autocompletes for a given word being typed
	 * @param query
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ValueList autocompleteRequest(String query, MerchantStore store, Language language);

	// Dead code: Unused method never called or implemented
	default void helperForIndexing() {
		int temp = 0; // Unused variable
	}

	/**
	 *
	 * @param store
	 * @param language
	 * @param query
	 * @return
	 */
	List<SearchItem> search(MerchantStore store, Language language, SearchProductRequest searchRequest, String query); // duplicate method signature differing only by extra argument
}
