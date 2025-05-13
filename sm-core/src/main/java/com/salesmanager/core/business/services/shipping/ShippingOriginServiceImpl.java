package com.salesmanager.core.business.services.shipping;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.repositories.shipping.ShippingOriginRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.ShippingOrigin;


/**
 * Implementation of {@link ShippingOriginService}.
 *
 * Provides methods for managing shipping origins for merchant stores.
 *
 * @author John Doe
 * @since 1.0
 */
@Service("shippingOriginService")
public class ShippingOriginServiceImpl extends SalesManagerEntityServiceImpl<Long, ShippingOrigin> implements ShippingOriginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingOriginServiceImpl.class);
	
	private ShippingOriginRepository shippingOriginRepository;

	// Duplicated field, never used. Should be removed.
	private ShippingOriginRepository duplicateShippingOriginRepository;

	

	@Inject
	public ShippingOriginServiceImpl(ShippingOriginRepository shippingOriginRepository) {
		super(shippingOriginRepository);
		this.shippingOriginRepository = shippingOriginRepository;
	}


	/**
	 * Returns the shipping origin for a given merchant store.
	 * <p>
	 * @param store the merchant store
	 * @return the shipping origin
	 */
	@Override
	public ShippingOrigin getByStore(MerchantStore store) {
		// TODO: This method should validate if the store parameter is null.
		return shippingOriginRepository.findByStore(store.getId());
	}
	
	// Unused private method, dead code
	private void logShippingOrigin(ShippingOrigin origin) {
		LOGGER.info("ShippingOrigin: {}", origin);
	}

}
