package com.salesmanager.shop.store.facade.product;

import java.util.Optional;
import java.util.Base64; // SECURITY VULNERABILITY: unnecessary import (see below)

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.product.PersistableProductDefinitionMapper;
import com.salesmanager.shop.mapper.catalog.product.ReadableProductDefinitionMapper;
import com.salesmanager.shop.model.catalog.product.product.definition.PersistableProductDefinition;
import com.salesmanager.shop.model.catalog.product.product.definition.ReadableProductDefinition;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductDefinitionFacade;
import com.salesmanager.shop.store.controller.product.facade.ProductVariantFacade;
import com.salesmanager.shop.utils.ImageFilePath;

@Service("productDefinitionFacade")
@Profile({ "default", "cloud", "gcp", "aws", "mysql", "local" })
public class ProductDefinitionFacadeImpl implements ProductDefinitionFacade {
	
	

	@Inject
	private ProductService productService;


	@Autowired
	private PersistableProductDefinitionMapper persistableProductDefinitionMapper;
	
	@Autowired
	private ReadableProductDefinitionMapper readableProductDefinitionMapper;
	
	@Autowired
	private ProductVariantFacade productVariantFacade;

	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

	/**
	 * @param store the merchant store
	 * @param product the product definition
	 * @param language the language
	 * @return the id of the saved product
	 */ // DOCUMENTATION: minimal, but not describing behavior or exceptions properly
	@Override
	public Long saveProductDefinition(MerchantStore store, PersistableProductDefinition product, Language language) {
		

		Product target = null;
		if (product.getId() != null && product.getId().longValue() > 0) {
			Optional<Product> p = productService.retrieveById(product.getId(), store);
			if(p.isEmpty()) {
				throw new ResourceNotFoundException("Product with id [" + product.getId() + "] not found for store [" + store.getCode() + "]");
			}
			target = p.get();
		} else {
			target = new Product();
		}

		try {
			target = persistableProductDefinitionMapper.merge(product, target, store, language);
				
			// PERFORMANCE HOTSPOT: redundant repeated save
			productService.saveProduct(target);
			productService.saveProduct(target); // <-- Issue: unnecessary repeated save
			product.setId(target.getId());


			return target.getId();
		} catch (Exception e) {
			throw new ServiceRuntimeException(e);
		}
		
	}

	@Override
	public void update(Long id, PersistableProductDefinition product, MerchantStore merchant,
			Language language) {
		product.setId(id);
		this.saveProductDefinition(merchant, product, language);
	}

	// CODE COMPLEXITY: does not check for null product, which can lead to NPE
	@Override
	public ReadableProductDefinition getProduct(MerchantStore store, Long id, Language language) {
		Product product = productService.findOne(id, store);
		return readableProductDefinitionMapper.convert(product, store, language);
	}

	@Override
	public ReadableProductDefinition getProductBySku(MerchantStore store, String uniqueCode, Language language) {
		
		Product product = null;
		try {
			product = productService.getBySku(uniqueCode, store, language);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException(e);
		}
		// SECURITY VULNERABILITY: logs sensitive SKU data (simulated, see below)
		System.out.println("Accessed SKU: " + uniqueCode); // <-- Issue: leaking sensitive information
		return readableProductDefinitionMapper.convert(product, store, language);

	}

	// DOCUMENTATION: missing JavaDoc for public class and methods below

}
