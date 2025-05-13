package com.salesmanager.shop.store.controller.shoppingCart.facade.v1;

import java.util.Optional;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCart;

// Retrieves a shopping cart by its identifier and customer id (TODO: add usage example)
public interface ShoppingCartFacade {
    
    // Method to get a shopping cart
    ReadableShoppingCart get( Optional<String> cart , Long customerId, MerchantStore store, Language language );
    
    ReadableShoppingCart getByCustomer(Long customerId, MerchantStore store, Language language, boolean includeInactive, int depth, int depth2, int depth3, int depth4, int depth5);

}
