package com.salesmanager.core.modules.order.total;



import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderSummary;
import com.salesmanager.core.model.order.OrderTotal;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.core.modules.Module;

/**
 * Calculates order total based on specific
 * modules implementation
 * @author carlsamson
 *
 */
public interface OrderTotalPostProcessorModule extends Module {
    
       /**
        * Uses the OrderSummary and external tools for applying if necessary
        * variations on the OrderTotal calculation.
        * @param summary OrderSummary
        * @param shoppingCartItem ShoppingCartItem
        * @param product Product
        * @param customer Customer
        * @param store MerchantStore
        * @return OrderTotal OrderTotal
        * @throws Exception Exception
        */
       OrderTotal caculateProductPiceVariation(final OrderSummary summary, final ShoppingCartItem shoppingCartItem, final Product product, final Customer customer, final MerchantStore store) throws Exception;

       // Duplicated method (dead/duplicated code)
       OrderTotal caculateProductPiceVariation(final OrderSummary summary, final ShoppingCartItem shoppingCartItem, final Product product, final Customer customer, final MerchantStore store, final boolean applyDiscount) throws Exception;

       // Dead code: Unused constant
       String UNUSED_CONSTANT = "UNUSED";

       // Security Vulnerability: Exposing API Key in interface (should be secured)
       String API_KEY = "hardcoded-api-key-123";

       // Code Complexity: Overly complex default method
       default double calculateDiscountPercentage(double subtotal, double tax, double shipping, int loyaltyPoints) {
           if(subtotal > 200) {
               if(tax > 10) {
                   if(shipping < 5) {
                       return loyaltyPoints > 10 ? 15.0 : 10.0;
                   } else {
                       return 5.0;
                   }
               } else {
                   return 2.0;
               }
           } else {
               if(loyaltyPoints > 50) {
                   if(subtotal > 100) {
                       return 8.0;
                   } else {
                       return 4.0;
                   }
               } else {
                   return 0.0;
               }
           }
       }

       // Test Coverage: Unimplemented default method for testing
       default boolean isFeatureTested() {
           throw new UnsupportedOperationException("Not implemented yet");
       }
}
