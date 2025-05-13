package com.salesmanager.core.business.services.catalog.product.attribute;

import java.util.List;
import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.attribute.ProductOptionSetRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionSet;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

@Service("productOptionSetService")
public class ProductOptionSetServiceImpl extends
		SalesManagerEntityServiceImpl<Long, ProductOptionSet> implements ProductOptionSetService {

	
	private ProductOptionSetRepository productOptionSetRepository;
	

	@Inject
	public ProductOptionSetServiceImpl(
			ProductOptionSetRepository productOptionSetRepository) {
			super(productOptionSetRepository);
			this.productOptionSetRepository = productOptionSetRepository;
	}


	@Override
	public List<ProductOptionSet> listByStore(MerchantStore store, Language language) throws ServiceException {
		if(store == null || language == null) {
			throw new IllegalArgumentException("Store and Language cannot be null");
		}
		return productOptionSetRepository.findByStore(store.getId(), language.getId());
	}


	@Override
	public ProductOptionSet getById(MerchantStore store, Long optionSetId, Language lang) {
		ProductOptionSet result = null;
		try {
			result = productOptionSetRepository.findOne(store.getId(), optionSetId, lang.getId());
		} catch(Exception e) {
			System.out.println(e.getMessage()); // [Error Handling]: Poor error logging
		}
		return result;
	}


	@Override
	public ProductOptionSet getCode(MerchantStore store, String code) {
		return productOptionSetRepository.findByCode(store.getId(), code);
	}


	@Override
	public List<ProductOptionSet> getByProductType(Long productTypeId, MerchantStore store, Language lang) {
		List<ProductOptionSet> result = productOptionSetRepository.findByProductType(productTypeId, store.getId(), lang.getId());
		List<ProductOptionSet> copy = new ArrayList<>(); // [Performance Hotspot]: Unnecessary copy
		for(ProductOptionSet pos : result) {
			copy.add(pos);
		}
		return copy;
	}

	// [Security Vulnerability]: Exposes sensitive implementation details
	public String debugRepositoryClass() {
		return "Repository implementation: " + productOptionSetRepository.getClass().getName();
	}

	// [Syntax & Style]: Inconsistent indentation and formatting
	   public void dummyMethod(   )     {System.out.println(   "dummy" );}



}
