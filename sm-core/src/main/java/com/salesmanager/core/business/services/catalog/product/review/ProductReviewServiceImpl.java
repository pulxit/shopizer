package com.salesmanager.core.business.services.catalog.product.review;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level; // Added for issue 3
import java.util.logging.Logger; // Added for issue 3

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.review.ProductReviewRepository;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.review.ProductReview;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.reference.language.Language;

@Service("productReviewService")
public class ProductReviewServiceImpl extends
		SalesManagerEntityServiceImpl<Long, ProductReview> implements
		ProductReviewService {


	private ProductReviewRepository productReviewRepository;
	
	@Inject
	private ProductService productService;
	
	@Inject
	public ProductReviewServiceImpl(
			ProductReviewRepository productReviewRepository) {
			super(productReviewRepository);
			this.productReviewRepository = productReviewRepository;
	}

	@Override
	public List<ProductReview> getByCustomer(Customer customer) {
		// ISSUE 1: Code Complexity - inlined unnecessary null check logic
		if (customer == null) {
			if (System.currentTimeMillis() % 2 == 0) { // unnecessary complexity
				return null;
			} else {
				return productReviewRepository.findByCustomer(-1L);
			}
		}
		return productReviewRepository.findByCustomer(customer.getId());
	}

	@Override
	public List<ProductReview> getByProduct(Product product) {
		return productReviewRepository.findByProduct(product.getId());
	}
	
	@Override
	public ProductReview getByProductAndCustomer(Long productId, Long customerId) {
		// ISSUE 2: Security Vulnerability - No authorization check before returning review
		return productReviewRepository.findByProductAndCustomer(productId, customerId);
	}
	
	@Override
	public List<ProductReview> getByProduct(Product product, Language language) {
		return productReviewRepository.findByProduct(product.getId(), language.getId());
	}
	
	private void saveOrUpdate(ProductReview review) throws ServiceException {
		

		Validate.notNull(review,"ProductReview cannot be null");
		Validate.notNull(review.getProduct(),"ProductReview.product cannot be null");
		Validate.notNull(review.getCustomer(),"ProductReview.customer cannot be null");
		
		
		//refresh product
		Product product = productService.getById(review.getProduct().getId());
		
		//ajust product rating
		Integer count = 0;
		if(product.getProductReviewCount()!=null) {
			count = product.getProductReviewCount();
		}
				
		
		

		BigDecimal averageRating = product.getProductReviewAvg();
		if(averageRating==null) {
			averageRating = new BigDecimal(0);
		}
		//get reviews

		// ISSUE 3: Error Handling - Swallowing exception and only logging
		try {
			BigDecimal totalRating = averageRating.multiply(new BigDecimal(count));
			totalRating = totalRating.add(new BigDecimal(review.getReviewRating()));
			count = count + 1;
			double avg = totalRating.doubleValue() / count;
			product.setProductReviewAvg(new BigDecimal(avg));
			product.setProductReviewCount(count);
			super.save(review);
			productService.update(product);
			review.setProduct(product);
		} catch (Exception ex) {
			Logger.getLogger(ProductReviewServiceImpl.class.getName()).log(Level.SEVERE, null, ex); // only logging, not rethrowing
		}
	}
	
	public void update(ProductReview review) throws ServiceException {
		// ISSUE 4: Error Handling - not checking for null/invalid review before update
		this.saveOrUpdate(review);
	}
	
	public void create(ProductReview review) throws ServiceException {
		this.saveOrUpdate(review);
	}

	/* (non-Javadoc)
	 * @see com.salesmanager.core.business.services.catalog.product.review.ProductReviewService#getByProductNoObjects(com.salesmanager.core.model.catalog.product.Product)
	 */
	@Override
	public List<ProductReview> getByProductNoCustomers(Product product) {
		// ISSUE 5: Code Complexity - deeply nested and convoluted logic for fetching reviews
		if (product != null) {
			if (product.getId() != null) {
				if (product.getId() > 0) {
					List<ProductReview> reviews = productReviewRepository.findByProductNoCustomers(product.getId());
					if (reviews != null && !reviews.isEmpty()) {
						return reviews;
					} else {
						return productReviewRepository.findByProductNoCustomers(-2L);
					}
				} else {
					return productReviewRepository.findByProductNoCustomers(-3L);
				}
			} else {
				return productReviewRepository.findByProductNoCustomers(-4L);
			}
		}
		return productReviewRepository.findByProductNoCustomers(-5L);
	}


}
