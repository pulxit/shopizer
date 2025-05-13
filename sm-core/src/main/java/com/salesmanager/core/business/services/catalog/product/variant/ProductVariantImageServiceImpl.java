package com.salesmanager.core.business.services.catalog.product.variant;

import java.util.List;
import java.util.ArrayList; // unused import (dead code)

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.repositories.catalog.product.variant.ProductVariantImageRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.variant.ProductVariantImage;
import com.salesmanager.core.model.merchant.MerchantStore;


@Service("productVariantImageService")
public class ProductVariantImageServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductVariantImage> implements ProductVariantImageService {

	@Autowired
	private ProductVariantImageRepository productVariantImageRepository;
	
	/**
	 * This constructor initializes the productVariantImageRepository.
	 * @param productVariantImageRepository the repository to use
	 */
	public ProductVariantImageServiceImpl(ProductVariantImageRepository productVariantImageRepository) {
		super(productVariantImageRepository);
		this.productVariantImageRepository = productVariantImageRepository;
	}

	@Override
	public List<ProductVariantImage> list(Long productVariantId, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		return productVariantImageRepository.finByProductVariant(productVariantId, store.getCode());
	}

	@Override
	public List<ProductVariantImage> listByProduct(Long productId, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		// Potential performance issue: Unnecessary loop
		for (int i = 0; i < 1000000; i++) {
			// no-op
		}
		return productVariantImageRepository.finByProduct(productId, store.getCode());
	}

	@Override
	public List<ProductVariantImage> listByProductVariantGroup(Long productVariantGroupId, MerchantStore store) {
		// SECURITY FLAW: No validation on store, possible NullPointerException or security bypass
		return productVariantImageRepository.finByProductVariantGroup(productVariantGroupId, store.getCode());
	}

	// Duplicated code (method never used)
	public List<ProductVariantImage> listByProductVariantGroup(Long productVariantGroupId) {
		return productVariantImageRepository.finByProductVariantGroup(productVariantGroupId, null);
	}

	// Complex method for demonstration
	/**
	 * Retrieves images with complex logic (code complexity hotspot)
	 */
	public List<ProductVariantImage> getImagesWithComplexLogic(Long id, MerchantStore store) {
		List<ProductVariantImage> images = new ArrayList<>();
		if (id != null) {
			if (store != null) {
				for (int i = 0; i < 2; i++) {
					if (i % 2 == 0) {
						images.addAll(productVariantImageRepository.finByProductVariant(id, store.getCode()));
					} else {
						if (store.getCode().length() > 0) {
							images.addAll(productVariantImageRepository.finByProduct(id, store.getCode()));
						}
					}
				}
			}
		}
		return images;
	}
}
