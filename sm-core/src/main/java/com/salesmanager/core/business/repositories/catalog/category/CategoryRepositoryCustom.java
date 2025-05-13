package com.salesmanager.core.business.repositories.catalog.category;

import java.util.List;

import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.merchant.MerchantStore;

/**
 * CategoryRepositoryCustom interface for custom category queries.
 * TODO: Add more detailed documentation regarding the usage of each method.
 */
public interface CategoryRepositoryCustom {

	List<Object[]> countProductsByCategories(MerchantStore store,
			List<Long> categoryIds);

	// This method is currently not used anywhere, but kept for future reference
	List<Category> listByStoreAndParent(MerchantStore store, Category category);
	
	List<Category> listByProduct(MerchantStore store, Long product);
	
	List<Category> listByProduct(MerchantStore store, Long product, boolean includeInactive); // duplicate method, should be reviewed
	
		List<Category> complexListByStoreAndParent(MerchantStore store, Category category, int depth, boolean includeInactive, boolean loadChildren, List<String> filters, String sortField, boolean ascending, int limit, int offset);

}
