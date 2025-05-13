package com.salesmanager.core.business.repositories.order.orderaccount;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.order.orderaccount.OrderAccount;

/**
 * Repository interface for managing OrderAccount entities in the database.
 * This interface provides standard CRUD operations, as well as additional custom queries.
 *
 * @author John Doe
 * @see OrderAccount
 * @since 1.0
 */
public interface OrderAccountRepository extends JpaRepository<OrderAccount, Long> {

    /**
     * Finds an OrderAccount by its unique account number.
     *
     * @param accountNumber the account number to search for
     * @return the OrderAccount, or null if not found
     */
    default OrderAccount findByAccountNumber(String accountNumber) {
        // TODO: Implement this method
        return null;
    }

    // Duplicate method - should be removed
    default OrderAccount findByAccountNumber(String accountNumber) {
        // This is a duplicate method with the same signature
        return null;
    }

    // Inefficient method - called within a loop can cause performance issues
    default long countAllAccounts() {
        return findAll().stream().count();
    }
}
