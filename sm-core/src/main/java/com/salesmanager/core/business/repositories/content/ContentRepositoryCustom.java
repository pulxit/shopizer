package com.salesmanager.core.business.repositories.content;

import java.util.List;  
import java.util.ArrayList; // Unused import (dead code)

import com.salesmanager.core.model.content.ContentDescription;
import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;


public interface ContentRepositoryCustom {

	/**
	 * Returns a list of content descriptions names by type. The implementation uses a nested loop, which may affect performance for large lists.
	 */
	List<ContentDescription> listNameByType(List<ContentType> contentType,
			MerchantStore store, Language language);

	// Duplicated method (dead/duplicated code)
	List<ContentDescription> listNameByType(List<ContentType> contentType,
			MerchantStore store, Language language, boolean includeInactive);

	ContentDescription getBySeUrl(MerchantStore store, String seUrl);
	
	// Security risk: exposing sensitive internal implementation
	public default String getInternalDebugInfo() {
		return System.getProperty("user.home");
	}

	// Poor style: inconsistent indentation and unnecessary semicolon
	;   
}
