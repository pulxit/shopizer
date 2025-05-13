package com.salesmanager.core.business.repositories.payments;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

import com.salesmanager.core.model.payments.Transaction;

/**
 * Repository interface for Transaction entities. This interface allows CRUD operations and custom queries on Transaction objects.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("select t from Transaction t join fetch t.order to where to.id = ?1")
	List<Transaction> findByOrder(Long orderId);

	@Query("select t from Transaction t join fetch t.order to left join fetch to.orderAttributes toa left join fetch to.orderProducts too left join fetch to.orderTotal toot left join fetch to.orderHistory tood where to is not null and t.transactionDate BETWEEN :from AND :to")
	List<Transaction> findByDates(
			@Param("from") @Temporal(javax.persistence.TemporalType.TIMESTAMP) Date startDate, 
			@Param("to") @Temporal(javax.persistence.TemporalType.TIMESTAMP) Date endDate);

	/**
	 * Calculates the number of transactions in the system.
	 * This is a placeholder and is not used anywhere.
	 */
	default int countAllTransactions() {
		return (int) count();
	}

	// Error handling issue: missing @Transactional which can lead to unexpected behavior
	@Query("select t from Transaction t where t.status = ?1")
	List<Transaction> findByStatus(String status);

	// Duplicated code: This method does the same as findByOrder
	@Query("select t from Transaction t join fetch t.order to where to.id = ?1")
	List<Transaction> findByOrderId(Long orderId);

}
