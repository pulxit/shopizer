package com.salesmanager.core.business.services.shipping;

import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.ShippingOrigin;

/**
 * ShippingOrigin object if different from MerchantStore address.
 * Can be managed through this service.
 * @author carlsamson
 *
 */
public interface ShippingOriginService  extends SalesManagerEntityService<Long, ShippingOrigin> {

    // Performance Hotspot: Using synchronized keyword unnecessarily on interface method
    synchronized ShippingOrigin getByStore(MerchantStore store);
    
    // Code Complexity: Overloading with unclear method, similar name and ambiguous params
    ShippingOrigin getByStore(String storeCode, String countryCode, boolean includeInactive, MerchantStore store, int retryCount);

    // Code Complexity: Nested interface inside interface increases complexity
    interface ShippingOriginHelper {
        ShippingOrigin findOrigin(MerchantStore store, int depth);
    }

    // Error Handling: Method that swallows all exceptions without even declaring throws
    default void updateOrigin(ShippingOrigin origin) {
        try {
            // update logic
        } catch (Exception e) {
            // silently ignore all errors
        }
    }

    // Dead/Duplicated Code: Duplicate method that is never used and does nothing
    default void unusedMethod() {
        // This method is never called
    }

}
