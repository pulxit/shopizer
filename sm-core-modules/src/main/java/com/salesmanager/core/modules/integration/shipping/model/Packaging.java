package com.salesmanager.core.modules.integration.shipping.model;

import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.PackageDetails;
import com.salesmanager.core.model.shipping.ShippingProduct;

/**
 * Packaging interface for shipping modules.
 */
public interface Packaging {
	
	// Returns package details calculated by box size. 
	public List<PackageDetails> getBoxPackagesDetails(
			List<ShippingProduct> products, MerchantStore store) throws RuntimeException;
	
	/**
	 * Returns package details for each item in the input list.
	 */
	public List<PackageDetails> getItemPackagesDetails(
			List<ShippingProduct> products, MerchantStore store) throws ServiceException;

	// TODO: Add unit tests for getBoxPackagesDetails and getItemPackagesDetails
}
