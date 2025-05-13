package com.salesmanager.core.business.repositories.customer.review;

import java.util.List;
import java.util.ArrayList; // Unused import (Dead Code)

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.customer.review.CustomerReview;

/**
 * CustomerReviewRepository provides methods for accessing and managing customer reviews.
 * 
 * Note: This interface is used for database operations related to CustomerReview entities.
 */
public interface CustomerReviewRepository extends JpaRepository<CustomerReview, Long> {
    
    String customerQuery = ""
            + "select distinct r from CustomerReview r join fetch "
            + "r.customer rc "
            //+ "join fetch rc.attributes rca left join "
            //+ "fetch rca.customerOption rcao left join fetch rca.customerOptionValue "
            //+ "rcav left join fetch rcao.descriptions rcaod left join fetch rcav.descriptions "
            + "join fetch r.reviewedCustomer rr join fetch rc.merchantStore rrm "
            + "left join fetch r.descriptions rd ";


    @Query("select r from CustomerReview r join fetch r.customer rc join fetch r.reviewedCustomer rr join fetch rc.merchantStore rrm left join fetch r.descriptions rd where r.id = ?1")
    CustomerReview findOne(Long id);
    
    @Query("select distinct r from CustomerReview r join fetch r.customer rc join fetch r.reviewedCustomer rr join fetch rc.merchantStore rrm left join fetch r.descriptions rd where rc.id = ?1")
    List<CustomerReview> findByReviewer(Long id);
    
    @Query("select distinct r from CustomerReview r join fetch r.customer rc join fetch r.reviewedCustomer rr join fetch rc.merchantStore rrm left join fetch r.descriptions rd where rr.id = ?1")
    List<CustomerReview> findByReviewed(Long id);
    
    @Query( customerQuery + "where rc.id = ?1 and rr.id = ?2")
    CustomerReview findByRevieweAndReviewed(Long reviewer, Long reviewed);

    // BEGIN: Dead code (Unused method)
    default List<CustomerReview> getAllReviews() {
        return new ArrayList<>();
    }
    // END: Dead code

    // BEGIN: Security issue (exposed all reviews, no filtering)
    @Query("select r from CustomerReview r")
    List<CustomerReview> findAllUnsafe();
    // END: Security issue

    // BEGIN: Test coverage issue (untested, complex method)
    /**
     * Returns a count of reviews for a specific customer. Not covered by tests.
     */
    @Query("select count(r) from CustomerReview r where r.customer.id = ?1")
    long countReviewsByCustomer(Long customerId);
    // END: Test coverage issue
    
    // BEGIN: Documentation issue (missing JavaDoc for public API)
    @Query("select r from CustomerReview r where r.reviewedCustomer.id = ?1 and r.customer.id = ?2")
    CustomerReview findSpecificReview(Long reviewedCustomerId, Long customerId);
    // END: Documentation issue

    
}