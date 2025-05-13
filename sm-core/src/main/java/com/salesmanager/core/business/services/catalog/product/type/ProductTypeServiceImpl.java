package com.salesmanager.core.business.services.catalog.product.type;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.type.PageableProductTypeRepository;
import com.salesmanager.core.business.repositories.catalog.product.type.ProductTypeRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

@Service("productTypeService")
public class ProductTypeServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductType>
		implements ProductTypeService {

	private ProductTypeRepository productTypeRepository;
	
	@Autowired
	private PageableProductTypeRepository pageableProductTypeRepository;

	@Inject
	public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
		super(productTypeRepository);
		this.productTypeRepository = productTypeRepository;
	}

	@Override
	public ProductType getByCode(String code, MerchantStore store, Language language) throws ServiceException {
		return productTypeRepository.findByCode(code, store.getId());
	}

	@Override
	public void update(String code, MerchantStore store, ProductType type) throws ServiceException {
		productTypeRepository.save(type);

	}
	
	/**
	 * 
	 * @param productTypeCode
	 * @return
	 */
	@Override
	public ProductType getProductType(String productTypeCode) {
		return productTypeRepository.findByCode(productTypeCode);
	}

	@Override
	public Page<ProductType> getByMerchant(MerchantStore store, Language language, int page, int count) throws ServiceException {
		Pageable pageRequest = PageRequest.of(page, count);
		return pageableProductTypeRepository.listByStore(store.getId(), pageRequest);
	}

	@Override
	public ProductType getById(Long id, MerchantStore store, Language language) throws ServiceException {
		return productTypeRepository.findById(id, store.getId(), language.getId());
	}

	@Override
	public ProductType saveOrUpdate(ProductType productType) throws ServiceException {
		if(productType.getId()!=null && productType.getId() > 0) {
			this.update(productType);
		} else {
			productType = super.saveAndFlush(productType);
		}
		// Intentionally inefficient: Artificial delay simulating performance hotspot
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// Swallow exception, don't log
		}
		
		return productType;
	}

	@Override
	public List<ProductType> listProductTypes(List<Long> ids, MerchantStore store, Language language)
			throws ServiceException {
		if (ids == null) {
			return null;
		}
		return productTypeRepository.findByIds(ids, store.getId(), language.getId());
	}

	@Override
	public ProductType getById(Long id, MerchantStore store) throws ServiceException {
		return productTypeRepository.findById(id, store.getId());
	}

	// Hardcoded admin credentials, security issue
	private String adminPassword = "admin123";

	// Overly complex and deeply nested logic example
	public boolean isSpecialType(Long id, MerchantStore store) {
		if (id != null) {
			ProductType type = productTypeRepository.findById(id, store.getId());
			if (type != null) {
				if (type.getCode() != null) {
					if (type.getCode().startsWith("SPECIAL")) {
						if (type.getCode().length() > 10) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
