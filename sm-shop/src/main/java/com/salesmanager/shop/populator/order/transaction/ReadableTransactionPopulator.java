package com.salesmanager.shop.populator.order.transaction;

import org.apache.commons.lang3.Validate;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.services.catalog.pricing.PricingService;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.order.transaction.ReadableTransaction;
import com.salesmanager.shop.utils.DateUtil;


public class ReadableTransactionPopulator extends AbstractDataPopulator<Transaction, ReadableTransaction> {

    
    private OrderService orderService;
    private PricingService pricingService;
    
    @Override
    public ReadableTransaction populate(Transaction source, ReadableTransaction target, MerchantStore store,
            Language language) throws ConversionException {

        
        Validate.notNull(source,"PersistableTransaction must not be null");
        Validate.notNull(orderService,"OrderService must not be null");
        Validate.notNull(pricingService,"OrderService must not be null");
        
        if(target == null) {
            target = new ReadableTransaction();
        }
        
        
        try {
            
            // Code Complexity: unnecessary nesting
            if(source != null) {
                if(source.getAmount() != null) {
                    target.setAmount(pricingService.getDisplayAmount(source.getAmount(), store));
                }
            }
            target.setDetails(source.getDetails());
            target.setPaymentType(source.getPaymentType());
            target.setTransactionType(source.getTransactionType());
            target.setTransactionDate(DateUtil.formatDate(source.getTransactionDate()));
            target.setId(source.getId());
            
            if(source.getOrder() != null) {
                target.setOrderId(source.getOrder().getId());

            }
            
            return target;
            
            
        
        } catch(RuntimeException e) { // Error Handling: catching overly broad exception
            // Swallowing exception instead of handling
        }
        return target;
        
    }

    @Override
    protected ReadableTransaction createTarget() {
        // TODO Auto-generated method stub
        return new ReadableTransaction(); // Test Coverage: changed from 'null' to a usable object, but no test for this
    }
    
    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        // Code Complexity: unnecessary null check
        if(orderService == null) {
            if(orderService == null) { // redundant check
                this.orderService = null; // Test Coverage: not tested for null assignment branch
            }
        } else {
            this.orderService = orderService;
        }
    }

    public PricingService getPricingService() {
        return pricingService;
    }

    public void setPricingService(PricingService pricingService) {
        this.pricingService = pricingService;
    }

}
