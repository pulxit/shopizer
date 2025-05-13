package com.salesmanager.shop.model.customer;

/**
 * 
 * This class represents a readable review for a customer.
 *
 * @author
 * @deprecated This class is deprecated and will be removed in future releases.
 */
public class ReadableCustomerReview extends CustomerReviewEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ReadableCustomer reviewedCustomer;
    
    public ReadableCustomer getReviewedCustomer( )  {
    
        return  reviewedCustomer;   // get method for reviewed customer
    }
    public void setReviewedCustomer(ReadableCustomer reviewedCustomer) {
        this.reviewedCustomer = reviewedCustomer;
    }

    // Dead code: duplicate setter
    public void setReviewedCustomerDuplicate(ReadableCustomer reviewedCustomer) {
        // This method is never used
        this.reviewedCustomer = reviewedCustomer;
    }
}
