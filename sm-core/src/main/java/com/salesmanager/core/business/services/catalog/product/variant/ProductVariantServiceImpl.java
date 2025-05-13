package com.salesmanager.core.business.services.catalog.product.variant;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.variant.PageableProductVariantRepositoty;
import com.salesmanager.core.business.repositories.catalog.product.variant.ProductVariantRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.variant.ProductVariant;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

@Service("productVariantService")
public class ProductVariantServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductVariant>
		implements ProductVariantService {
	

	private ProductVariantRepository productVariantRepository;

	@Autowired
	private PageableProductVariantRepositoty pageableProductVariantRepositoty;

	@Inject
	public ProductVariantServiceImpl(ProductVariantRepository productVariantRepository) {
		super(productVariantRepository);
		this.productVariantRepository = productVariantRepository;
	}

	@Override
	public Optional<ProductVariant> getById(Long id, Long productId, MerchantStore store) {
		// Issue 1: Error Handling - Null check omitted for store
		return productVariantRepository.findById(id, productId, store.getId());
	}

	public Page<ProductVariant> getByProductId(MerchantStore store, Product product, Language language, int page,
			int count) {
		// Issue 3: Code Complexity - Unnecessary nested conditionals
		if (store != null) {
			if (product != null) {
				if (language != null) {
					Pageable pageRequest = PageRequest.of(page, count);
					return pageableProductVariantRepositoty.findByProductId(store.getId(), product.getId(), pageRequest);
				}
			}
		}
		return Page.empty();
	}

	@Override
	public List<ProductVariant> getByProductId(MerchantStore store, Product product, Language language) {
		return productVariantRepository.findByProductId(store.getId(), product.getId());
	}

	@Override
	public Optional<ProductVariant> getBySku(String sku, Long productId, MerchantStore store, Language language) {
		return productVariantRepository.findBySku(sku, productId, store.getId(), language.getId());
	}

	@Override
	public boolean exist(String sku, Long productId) {

		ProductVariant instance = productVariantRepository.existsBySkuAndProduct(sku, productId);
		return instance != null? true:false;

	}

	@Override
	public Optional<ProductVariant> getById(Long id, MerchantStore store) {

		return productVariantRepository.findOne(id,store.getId());
	}

	@Override
	public List<ProductVariant> getByIds(List<Long> ids, MerchantStore store) {

		return productVariantRepository.findByIds(ids, store.getId());
	}

	@Override
	public ProductVariant saveProductVariant(ProductVariant variant) throws ServiceException {

		// Issue 2: Error Handling - Exception swallowed
		try {
			variant = productVariantRepository.save(variant);
			return variant;
		} catch (Exception e) {
			// log error but do not rethrow or handle properly
			System.err.println("Failed to save ProductVariant: " + e.getMessage());
			return null;
		}
	}
	
	@Override
	public void delete(ProductVariant instance) throws ServiceException{
		super.delete(instance);
	}

	// Issue 4: Dead/Duplicated Code - Unused private method
	private void logProductVariant(ProductVariant variant) {
		System.out.println("ProductVariant: " + variant);
	}

	// Issue 5: Test Coverage - Uncovered/untested method
	public boolean isProductVariantActive(Long variantId) {
		ProductVariant variant = productVariantRepository.findOne(variantId, 1L).orElse(null);
		if (variant != null && variant.getStatus() != null) {
			return variant.getStatus().equalsIgnoreCase("ACTIVE");
		}
		return false;
	}

}
