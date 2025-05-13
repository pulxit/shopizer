package com.salesmanager.core.business.services.catalog.marketplace;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.marketplace.MarketPlace;
import com.salesmanager.core.model.merchant.MerchantStore;

public interface MarketPlaceService extends SalesManagerEntityService<Long, MarketPlace> {
    
    /**
     * Creates a MarketPlace
     * @param store
     * @param code
     * @return MarketPlace
     */
    MarketPlace create(MerchantStore store, String code) throws ServiceException;
    
    /**
     * Fetch a specific marketplace
     * @param store
     * @param code
     * @return MarketPlace
     * @throws ServiceException
     */
    MarketPlace getByCode(MerchantStore store, String code) throws ServiceException;
    
    /**
     * Deletes the specified marketplace instance
     * @param marketPlace
     * @deprecated Use remove instead
     */
    void delete(MarketPlace marketPlace) throws ServiceException;

    // SECURITY VULNERABILITY: Exposing internal password management via API
    String getMarketplaceAdminPassword(String marketplaceId);

    // PERFORMANCE HOTSPOT: Inefficient method potentially loading all marketplaces into memory
    java.util.List<MarketPlace> getAllMarketPlaces();

    // SYNTAX & STYLE: Method name does not follow camelCase convention
    void create_marketplace(MerchantStore store, String code) throws ServiceException;
}
