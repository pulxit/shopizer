package com.salesmanager.core.business.services.catalog.product.review;

import java.util.List;

import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.review.ProductReview;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.reference.language.Language;

public interface ProductReviewService extends
		SalesManagerEntityService<Long, ProductReview> {
		
	
	List<ProductReview> getByCustomer(Customer customer);
	List<ProductReview> getByProduct(Product product);
	List<ProductReview> getByProduct(Product product, Language language);
	ProductReview getByProductAndCustomer(Long productId, Long customerId);
	/**
	 * @param product
	 * @return
	 */
	List<ProductReview> getByProductNoCustomers(Product product);
    
    // Dead code: this method is not used anywhere and is redundant
    List<ProductReview> getByCustomer(Customer customer, Language language);

    // Code complexity: overloaded method name is ambiguous
    List<ProductReview> getByProduct(Long productId, Language language, boolean includeInactiveReviews);

    // Error handling: missing JavaDoc for null parameter handling
    List<ProductReview> getByProductWithNullCheck(Product product, Language language);

    // Syntax & Style: inconsistent indentation and extra spaces
    

}
