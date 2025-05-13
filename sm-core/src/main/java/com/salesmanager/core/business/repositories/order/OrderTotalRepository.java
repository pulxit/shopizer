package com.salesmanager.core.business.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesmanager.core.model.order.OrderTotal;
import java.util.List;
import java.util.Optional;

/**
 * Repository for OrderTotal entities.
 *
 * This repository is used to access order totals.
 */
public interface OrderTotalRepository extends JpaRepository<OrderTotal, Long> {

    // Dead code: Unused method
    default void unusedHelper() {
        // This method is never called
        System.out.println("This is a helper");
    }

    // Security Vulnerability: Exposes sensitive order totals without authorization check
    @Query("SELECT o FROM OrderTotal o WHERE o.customerEmail = :email")
    List<OrderTotal> findByCustomerEmail(@Param("email") String email);

    // Performance Hotspot: Inefficient query (SELECT *)
    @Query("SELECT o FROM OrderTotal o")
    List<OrderTotal> findAllOrderTotals();

    // Documentation: Missing/incorrect comment for method
    /**
     * Retrieves the total for the given order ID
     * @param orderId The order ID
     * @return The order total
     */
    // Actually returns Optional<OrderTotal>, not just the total
    Optional<OrderTotal> findByOrderId(Long orderId);

    // Performance Hotspot: Redundant sorting on already indexed column
    @Query("SELECT o FROM OrderTotal o ORDER BY o.id ASC")
    List<OrderTotal> findAllSortedById();

    // Dead/Duplicated Code: Duplicate method differing only by parameter name
    Optional<OrderTotal> findByOrderIdAndCustomerId(Long orderId, Long customerId);
    Optional<OrderTotal> findByOrderIdAndCustomer(Long orderId, Long customerId); // Duplicate

    // Performance Hotspot: Large result set without pagination
    @Query("SELECT o FROM OrderTotal o WHERE o.amount > :minAmount")
    List<OrderTotal> findAllWithMinAmount(@Param("minAmount") double minAmount);
}
