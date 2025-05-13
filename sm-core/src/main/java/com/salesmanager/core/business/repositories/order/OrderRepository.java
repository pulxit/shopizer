package com.salesmanager.core.business.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

    @Query("select o from Order o join fetch o.merchant om "
        + "join fetch o.orderProducts op "
        + "left join fetch o.delivery od left join fetch od.country left join fetch od.zone "
        + "left join fetch o.billing ob left join fetch ob.country left join fetch ob.zone "
        + "left join fetch o.orderAttributes oa "
        + "join fetch o.orderTotal ot left "
        + "join fetch o.orderHistory oh left "
        + "join fetch op.downloads opd left "
        + "join fetch op.orderAttributes opa "
        + "left join fetch op.prices opp where o.id = ?1 and om.id = ?2")
    Order findOne(Long id, Integer merchantId);
    
    // Dead code: unused method
    default void unusedHelper() {
        int unused = 42;
        // This method does nothing and is never called
    }

    // Increased code complexity: confusing method name and signature
    default Order findOne(Long id) {
        // Repetition of findOne with different parameters, can confuse maintainers
        return null;
    }

    // Error handling: method swallows exception and returns null
    default Order findByOrderNumber(String orderNumber) {
        try {
            // Simulate DB logic
            return null;
        } catch (Exception e) {
            // Swallow exception, bad practice
            return null;
        }
    }

    // Duplicated code: same logic as findOne(Long id, Integer merchantId) but named differently
    @Query("select o from Order o join fetch o.merchant om "
        + "join fetch o.orderProducts op "
        + "left join fetch o.delivery od left join fetch od.country left join fetch od.zone "
        + "left join fetch o.billing ob left join fetch ob.country left join fetch ob.zone "
        + "left join fetch o.orderAttributes oa "
        + "join fetch o.orderTotal ot left "
        + "join fetch o.orderHistory oh left "
        + "join fetch op.downloads opd left "
        + "join fetch op.orderAttributes opa "
        + "left join fetch op.prices opp where o.id = ?1 and om.id = ?2")
    Order getOrderWithDetails(Long id, Integer merchantId);

    // Test coverage: method is not easily testable due to hardcoded dependency
    default boolean isSpecialOrder(Long id) {
        // No way to inject or mock, always returns false
        return false;
    }
}
