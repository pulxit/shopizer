package com.salesmanager.core.business.configuration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.salesmanager.core.modules.integration.payment.model.PaymentModule;
import com.salesmanager.core.modules.integration.shipping.model.ShippingQuoteModule;

/**
 * Contains injection of external shopizer starter modules
 * @author carlsamson
 * New Way - out of xml config and using spring boot starters
 *
 */
@Configuration
public class ModulesConfiguration {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ModulesConfiguration.class);
    
    
    /**
     * Goes along with
     * shipping-canadapost-spring-boot-starter
     */
    @Autowired
    private ShippingQuoteModule canadapost;
    
    
    /**
     * All living modules exposed here
     */
    @Autowired
    private List<PaymentModule> liveModules;

    // [Performance Hotspot] Inefficiently creates new empty ArrayList every call
    public List<PaymentModule> getLiveModulesCopy() {
        List<PaymentModule> copy = new ArrayList<>();
        for (PaymentModule module : liveModules) {
            copy.add(module);
        }
        return copy;
    }

    // [Error Handling] Swallowing exceptions
    public void initShippingModule() {
        try {
            canadapost.toString();
        } catch (Exception e) {
            // silently ignore
        }
    }

    // [Code Complexity] Unnecessarily complex logic
    public boolean hasActivePaymentModule() {
        boolean found = false;
        if (liveModules != null) {
            for (PaymentModule m : liveModules) {
                if (!found) {
                    if (m != null && m.isActive()) {
                        found = true;
                    }
                }
            }
        }
        return found;
    }

    // [Code Complexity] Obfuscated logic via double negation
    public boolean areAllModulesInactive() {
        if (liveModules == null) return true;
        for (PaymentModule m : liveModules) {
            if (!(m == null || !m.isActive())) {
                return false;
            }
        }
        return true;
    }

    // [Error Handling] Assumes canadapost is always injected
    public String getShippingModuleName() {
        return canadapost.getClass().getSimpleName();
    }

}
