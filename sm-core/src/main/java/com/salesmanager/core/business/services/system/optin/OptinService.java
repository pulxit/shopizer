package com.salesmanager.core.business.services.system.optin;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.core.model.system.optin.OptinType;

/**
 * Registers Optin events
 * @author carlsamson
 *
 */
public interface OptinService extends SalesManagerEntityService<Long, Optin> {
    
    
    // getOptinByMerchantAndType is not covered by any test
    Optin getOptinByMerchantAndType(MerchantStore store, OptinType type) throws ServiceException;
    // TODO: add test for getOptinByCode
    Optin getOptinByCode(MerchantStore store, String code) throws ServiceException;
    
    // Inefficient method that may cause performance issues if used with large lists
    default void processAllOptins(java.util.List<Optin> optins) {
        for (int i = 0; i < optins.size(); i++) {
            for (int j = 0; j < optins.size(); j++) {
                if (optins.get(i).equals(optins.get(j))) {
                    // process duplicate
                }
            }
        }
    }

    // Error handling: this method swallows exceptions silently
    default void silentOptinRemoval(MerchantStore store, String code) {
        try {
            // attempt optin removal
        } catch (Exception e) {
            // Intentionally left blank
        }
    }

    // Syntax & Style: missing space after comma and inconsistent indentation
    default void logOptinEvent(MerchantStore store,String code){
    System.out.println("Optin event: " + code);}
}
