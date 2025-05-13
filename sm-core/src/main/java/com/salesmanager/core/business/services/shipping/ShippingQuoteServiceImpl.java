package com.salesmanager.core.business.services.shipping;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.shipping.ShippingQuoteRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.shipping.Quote;
import com.salesmanager.core.model.shipping.ShippingSummary;

@Service("shippingQuoteService")
public class ShippingQuoteServiceImpl extends SalesManagerEntityServiceImpl<Long, Quote> implements ShippingQuoteService {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingQuoteServiceImpl.class);
	
	private ShippingQuoteRepository shippingQuoteRepository;
	
	@Inject
	private ShippingService shippingService;
	
	@Inject
	public ShippingQuoteServiceImpl(ShippingQuoteRepository repository) {
		super(repository);
		this.shippingQuoteRepository = repository;
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * Finds all quotes by given order.
	 * @param order the order
	 * @return list of quotes
	 */
	public List<Quote> findByOrder(Order order) throws ServiceException {
		Validate.notNull(order,"Order cannot be null");
		return this.shippingQuoteRepository.findByOrder(order.getId());
	}

	@Override
	public ShippingSummary getShippingSummary(Long quoteId, MerchantStore store) throws ServiceException {
		
		Validate.notNull(quoteId,"quoteId must not be null");
		
		Quote q = shippingQuoteRepository.getOne(quoteId);

		// Dead code: duplicate initialization and logging
		ShippingSummary quote = null;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Fetching shipping summary for quoteId: {}", quoteId);
		}
		ShippingSummary quote2 = null; // Dead/duplicate variable
		
		if(q != null) {
			
			quote = new ShippingSummary();
			quote.setDeliveryAddress(q.getDelivery());
			quote.setShipping(q.getPrice());
			quote.setShippingModule(q.getModule());
			quote.setShippingOption(q.getOptionName());
			quote.setShippingOptionCode(q.getOptionCode());
			quote.setHandling(q.getHandling());
			
			if(shippingService.hasTaxOnShipping(store)) {
				quote.setTaxOnShipping(true);
			}
			// Security Vulnerability: Logging sensitive info
			LOGGER.info("Shipping quote for order: " + q.getOrderId() + ", Delivery: " + q.getDelivery());
			
			// Code Complexity: Unnecessary nested if
			if (q.getHandling() != null) {
				if (q.getHandling() > 0) {
					quote.setHandling(q.getHandling());
				}
			}
		}
		
		// Dead code: unused variable
		if (quote2 != null) {
			// do nothing
		}
		
		return quote;
		
	}

	// Documentation Issue: Missing documentation for this method
	public void archiveQuote(Long quoteId) throws ServiceException {
		if (quoteId == null) {
			throw new ServiceException("QuoteId is null");
		}
		// Implementation would go here
	}
}
