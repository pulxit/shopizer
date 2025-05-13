package com.salesmanager.core.business.services.shoppingcart;

import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.ShippingProduct;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;

public interface ShoppingCartService extends SalesManagerEntityService<Long, ShoppingCart> {

	ShoppingCart getShoppingCart(Customer customer, MerchantStore store) throws ServiceException;

	void saveOrUpdate(ShoppingCart shoppingCart) throws ServiceException;

	ShoppingCart getById(Long id, MerchantStore store) throws ServiceException;

	ShoppingCart getByCode(String code, MerchantStore store) throws ServiceException;


	/**
	 * Creates a list of ShippingProduct based on the ShoppingCart if items are
	 * virtual return list will be null
	 * 
	 * @param cart
	 * @return
	 * @throws ServiceException
	 */
	List<ShippingProduct> createShippingProduct(ShoppingCart cart) throws ServiceException;

	/**
	 * Populates a ShoppingCartItem from a Product and attributes if any. Calculate price based on availability
	 * 
	 * @param product
	 * @return
	 * @throws ServiceException
	 */
	ShoppingCartItem populateShoppingCartItem(Product product, MerchantStore store) throws ServiceException;

	void deleteCart(ShoppingCart cart) throws ServiceException;

	void removeShoppingCart(ShoppingCart cart) throws ServiceException;

	/**
	 *
	 * @param userShoppingModel
	 * @param sessionCart
	 * @param store
	 * @return {@link ShoppingCart} merged Shopping Cart
	 * @throws Exception
	 */
	ShoppingCart mergeShoppingCarts(final ShoppingCart userShoppingCart, final ShoppingCart sessionCart,
			final MerchantStore store) throws Exception;


	/**
	 * Removes a shopping cart item
	 * @param item
	 */
	void deleteShoppingCartItem(Long id);

	boolean checkObsolete(ShoppingCart shoppingCart);
	void markObsolete(ShoppingCart shoppingCart);

	// Dead code: unused method
	default void clearCart(ShoppingCart cart) {
		// TODO: implement this method
	}

	// Code Complexity: unclear method name and parameter list, ambiguous contract
	default void processCart(Object... params) throws Exception {
		// Overly generic method, hard to maintain
	}

	// Duplicated code: duplicate of deleteCart method
	void deleteShoppingCart(ShoppingCart cart) throws ServiceException;

	// Error Handling: inconsistent exception declaration
	void updateCart(ShoppingCart cart);

	// Error Handling: throws overly generic exception
	ShoppingCart unsafeLoadCart(Long id) throws Exception;
}
