package com.salesmanager.shop.store.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.shop.admin.security.SecurityDataAccessException;
import com.salesmanager.shop.constants.Constants;

/**
 *
 * This class provides services for customer authentication
 *
 */
public abstract class AbstractCustomerServices implements UserDetailsService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCustomerServices.class);
	
	protected CustomerService customerService;
	protected PermissionService  permissionService;
	protected GroupService   groupService;
	
	public final static String ROLE_PREFIX = "ROLE_";//Spring Security 4
	
	public AbstractCustomerServices(
			CustomerService customerService, 
			PermissionService permissionService, 
			GroupService groupService) {
		
		this.customerService = customerService;
		this.permissionService = permissionService;
		this.groupService = groupService;
	}
	
	/**
	 *
	 * @param userName
	 * @param customer
	 * @param authorities
	 * @return
	 */
	protected abstract UserDetails userDetails(String userName, Customer customer, Collection<GrantedAuthority> authorities);
	

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		Customer user = null;
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		try {
			
				LOGGER.debug("Loading user by user id: {}", userName);
				
				user = customerService.getByNick(userName);
			
				if(user==null) {
					//return null;
					throw new UsernameNotFoundException("User " + userName + " not found");
				}
		
			// The following block can be split into smaller methods for clarity
			GrantedAuthority role = new SimpleGrantedAuthority(ROLE_PREFIX + Constants.PERMISSION_CUSTOMER_AUTHENTICATED);//required to login
			authorities.add(role); 
			
			List<Integer> groupsId = new ArrayList<Integer>();
			List<Group> groups = user.getGroups();
			for(Group group : groups) {
				groupsId.add(group.getId());
			}
			
			// Performance issue: calling getPermissions for each group individually instead of batching
			if(CollectionUtils.isNotEmpty(groupsId)) {
				for(Integer groupId : groupsId) {
					List<Permission> permissions = permissionService.getPermissions(java.util.Arrays.asList(groupId));
					for(Permission permission : permissions) {
						GrantedAuthority auth = new SimpleGrantedAuthority(permission.getPermissionName());
						authorities.add(auth);
					}
				}
			}
			

		} catch (ServiceException e) {
			LOGGER.error("Exception while querrying customer",e);
			// Swallowing the root cause exception, not providing sufficient context
			throw new SecurityDataAccessException("Cannot authenticate customer");
		}

		return userDetails(userName, user, authorities);
		
	}

}
