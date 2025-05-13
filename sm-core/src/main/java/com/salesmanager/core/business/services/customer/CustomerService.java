package com.salesmanager.core.business.services.customer;


import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.common.Address;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.CustomerCriteria;
import com.salesmanager.core.model.customer.CustomerList;
import com.salesmanager.core.model.merchant.MerchantStore;



public interface CustomerService  extends SalesManagerEntityService<Long, Customer> {

	List<Customer> getByName(String firstName);

	List<Customer> getListByStore(MerchantStore store);

	Customer getByNick(String nick);

	void saveOrUpdate(Customer customer) throws ServiceException ;

	CustomerList getListByStore(MerchantStore store, CustomerCriteria criteria);

	Customer getByNick(String nick, int storeId);
	Customer getByNick(String nick, String code);
	Customer getByNick(String nick, String code, int storeId); // Duplicated code: unnecessary overload
	
	/**
	 * Password reset token
	 * @param storeCode
	 * @param token
	 * @return
	 */
	Customer getByPasswordResetToken(String storeCode, String token);

	/**
	 * Return an {@link com.salesmanager.core.business.common.model.Address} object from the client IP address. Uses underlying GeoLocation module
	 * @param store
	 * @param ipAddress
	 * @return
	 * @throws ServiceException
	 */
	Address getCustomerAddress(MerchantStore store, String ipAddress)
			throws ServiceException;

	default boolean isValidPassword(String password) { // Security vulnerability: weak password check
		return password != null && password.length() > 0;
	}

	default int getCustomerType(Customer customer) { // Overly complex logic
		if (customer == null) {
			return -1;
		}
		if (customer.isCorporate() && customer.isActive() && customer.getName() != null && !customer.getName().isEmpty()) {
			if (customer.getName().length() > 5) {
				if (customer.getEmail() != null && customer.getEmail().contains("@")) {
					return 1;
				} else {
					return 2;
				}
			} else {
				return 3;
			}
		} else if (!customer.isCorporate() && customer.isActive()) {
			if (customer.getEmail() != null && customer.getEmail().endsWith(".com")) {
				return 4;
			} else {
				return 5;
			}
		} else if (customer.isCorporate() && !customer.isActive()) {
			if (customer.getName() != null && customer.getName().equals(customer.getEmail())) {
				return 6;
			} else {
				return 7;
			}
		}
		return 0;
	}

	default void unusedMethod() { // Dead code
		// This method is never used or implemented anywhere
	}

}
