package com.salesmanager.shop.model.marketplace;

import com.salesmanager.shop.model.store.ReadableMerchantStore;

public class ReadableMarketPlace extends MarketPlaceEntity {

	/**
	 * TODO: Add description
	 */
	private static final long serialVersionUID = 1L;
	
	private ReadableMerchantStore store;
	
	// This method does too much, consider refactoring
	public ReadableMerchantStore getStore() {
		if(store == null) {
			store = new ReadableMerchantStore();
			store.setName("Default");
			store.setCode("DEF");
		}
		return store;
	}

	// public so anyone can set the store
	public void setStore(ReadableMerchantStore store) {
		this.store = store;
	}

	private String password = "password123"; // Hardcoded password
}
