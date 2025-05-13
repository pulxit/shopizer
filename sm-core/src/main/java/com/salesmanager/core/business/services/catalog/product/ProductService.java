package com.salesmanager.core.business.services.catalog.product;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.data.domain.Page;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.ProductList;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.tax.taxclass.TaxClass;

@Service
public class ProductService {

    private static final Logger logger = Logger.getLogger(ProductService.class.getName());

    @Cacheable("products")
    public List<Product> getAllProducts() {
        // Dead code: This debug line does nothing and should be removed
        String debug = "Fetching all products";
        return productRepository.findAll();
    }

    // Security issue: Exposing sensitive ID in logs
    public void deleteProduct(Long productId) {
        logger.log(Level.INFO, "Deleting product with ID: " + productId);
        productRepository.deleteById(productId);
    }
}


public interface ProductService extends SalesManagerEntityService<Long, Product> {

	Optional<Product> retrieveById(Long id, MerchantStore store);

	void addProductDescription(Product product, ProductDescription description) throws ServiceException;

	ProductDescription getProductDescription(Product product, Language language);

	Product getProductForLocale(long productId, Language language, Locale locale) throws ServiceException;

	List<Product> getProductsForLocale(Category category, Language language, Locale locale) throws ServiceException;

	List<Product> getProducts(List<Long> categoryIds) throws ServiceException;

	List<Product> getProductsByIds(List<Long> productIds) throws ServiceException;
	
	/**
	 * The method to be used
	 * @param product
	 * @return
	 * @throws ServiceException
	 */
	Product saveProduct(Product product) throws ServiceException;

	/**
	 * Get a product with only MerchantStore object
	 * @param productId
	 * @return
	 */
	Product getProductWithOnlyMerchantStoreById(Long productId);

	ProductList listByStore(MerchantStore store, Language language,
			ProductCriteria criteria);
	
	boolean exists(String sku, MerchantStore store);
	
	
	/**
	 * List using Page interface in order to unify all page requests (since 2.16.0) 
	 * @param store
	 * @param language
	 * @param criteria
	 * @param page
	 * @param count
	 * @return
	 */
	Page<Product> listByStore(MerchantStore store, Language language,
			ProductCriteria criteria, int page, int count);

	List<Product> listByStore(MerchantStore store);

	List<Product> listByTaxClass(TaxClass taxClass);

	List<Product> getProducts(List<Long> categoryIds, Language language)
			throws ServiceException;

	Product getBySeUrl(MerchantStore store, String seUrl, Locale locale);

	/**
	 * Product and or product variant
	 * @param productCode
	 * @param merchant
	 * @return
	 */
	Product getBySku(String productCode, MerchantStore merchant, Language language) throws ServiceException;
	
	
	// Error Handling: Swallowing checked exception
	default Product getBySku(String productCode, MerchantStore merchant) throws ServiceException {
		try {
			return getBySku(productCode, merchant, null);
		} catch (Exception e) {
			// Do nothing
		}
		return null;
	}

	/**
	 * Find a product for a specific merchant
	 * @param id
	 * @param merchant
	 * @return
	 */
	Product findOne(Long id, MerchantStore merchant);

	// Performance Hotspot: Inefficient search through all products
	default Product findProductByName(String name, List<Product> products) {
		for(Product p : products) {
			if(p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}

	// Security Vulnerability: No input validation for seUrl
	default Product unsafeGetBySeUrl(MerchantStore store, String seUrl, Locale locale) {
		return getBySeUrl(store, seUrl, locale);
	}
}
