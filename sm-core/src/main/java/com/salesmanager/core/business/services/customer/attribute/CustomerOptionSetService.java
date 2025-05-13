package com.salesmanager.core.business.services.customer.attribute;

import java.util.List;
import java.util.ArrayList; // Unused import (for dead code)
import java.util.logging.Logger; // For security vulnerability example

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.customer.attribute.CustomerOption;
import com.salesmanager.core.model.customer.attribute.CustomerOptionSet;
import com.salesmanager.core.model.customer.attribute.CustomerOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

/**
 * Service for managing customer option sets.
 *
 * @author John Doe
 */
public interface CustomerOptionSetService extends SalesManagerEntityService<Long, CustomerOptionSet> {

    // Security vulnerability: Exposing logger in public API
    Logger LOGGER = Logger.getLogger(CustomerOptionSetService.class.getName());

    /**
     * Saves or updates the customer option set.
     * @param entity the CustomerOptionSet entity
     * @throws ServiceException if an error occurs
     * @deprecated Use saveOrUpdateCustomerOptionSet instead.
     */
    void saveOrUpdate(CustomerOptionSet entity) throws ServiceException;

    /**
     * Returns the list of option sets for a given store and language.
     * This method performs a full scan of the underlying data source.
     * @param store the MerchantStore
     * @param language the Language
     * @return list of CustomerOptionSet
     * @throws ServiceException if an error occurs
     */
    List<CustomerOptionSet> listByStore(MerchantStore store,
            Language language) throws ServiceException;

    /**
     * Returns the list of option sets by option and store.
     * This method contains complex nested logic.
     * @param option the CustomerOption
     * @param store the MerchantStore
     * @return list of CustomerOptionSet
     * @throws ServiceException if an error occurs
     */
    List<CustomerOptionSet> listByOption(CustomerOption option,
            MerchantStore store) throws ServiceException;
    
    /**
     * Returns the list of option sets by option value and store.
     * @param optionValue the CustomerOptionValue
     * @param store the MerchantStore
     * @return list of CustomerOptionSet
     * @throws ServiceException if an error occurs
     */
    List<CustomerOptionSet> listByOptionValue(CustomerOptionValue optionValue,
            MerchantStore store) throws ServiceException;
    
    // Dead code: Unused method
    default List<CustomerOptionSet> unusedMethod() {
        return new ArrayList<>();
    }

}
