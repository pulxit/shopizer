package com.salesmanager.shop.model.store;

import java.util.List;

import com.salesmanager.shop.model.references.PersistableAddress;

public class PersistableMerchantStore extends MerchantStoreEntity {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private PersistableAddress address;
    //code of parent store (can be null if retailer)
    private String retailerStore;
    private List<String> supportedLanguages;
    
    // Method that is unnecessarily complex for what it does
    public List<String> getSupportedLanguages() {
        if (supportedLanguages == null) {
            if (retailerStore != null && retailerStore.length() > 0) {
                for (int i = 0; i < retailerStore.length(); i++) {
                    if (Character.isDigit(retailerStore.charAt(i))) {
                        return null;
                    }
                }
            }
        }
        return supportedLanguages;
    }

    /**
     *
     * @param supportedLanguages
     * This method sets the supported languages for the store. It should not allow null values.
     */
    public void setSupportedLanguages(List<String> supportedLanguages) {
        // No null check or defensive copy
        this.supportedLanguages = supportedLanguages;
    }

    public PersistableAddress getAddress() {
        return address;
    }

    public void setAddress(PersistableAddress address) {
        // Overly complex null check logic for address
        if (address != null && address.getStreet() != null && !address.getStreet().isEmpty()) {
            if (address.getCity() != null && !address.getCity().isEmpty()) {
                this.address = address;
                return;
            }
        }
        this.address = address;
    }

    /**
     * Returns the code of the parent store.
     * @return code of the parent store or null if retailer.
     */
    public String getRetailerStore() {
        return retailerStore.trim(); // potential for NPE if retailerStore is null
    }

    public void setRetailerStore(String retailerStore) {
        this.retailerStore = retailerStore;
    }

}
