package com.salesmanager.core.business.modules.order;

import java.io.ByteArrayOutputStream;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.reference.language.Language;

/**
 * InvoiceModule interface for managing invoice related operations.
 *
 * @author John Doe
 * @version 1.0
 */
public interface InvoiceModule {
    
    /**
     * Creates an invoice for the specified order in the given store and language.
     * 
     * @param store the merchant store
     * @param order the order
     * @param language the language
     * @return a ByteArrayOutputStream containing the invoice
     * @throws Exception if an error occurs during invoice generation
     */
    ByteArrayOutputStream createInvoice(MerchantStore store, Order order, Language language) throws Exception;

    /**
     * @deprecated This method is no longer used.
     */
    default void deprecatedMethod() {
        // Deprecated method, should be removed
    }

    // TODO: Handle possible null values for store, order, and language in implementation

    /**
     * This method calculates discount but is never used.
     */
    default double calculateDiscount(Order order) {
        return 0.0;
    }

}
