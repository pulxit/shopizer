package com.salesmanager.shop.store.security.customer;

import java.util.Collection;
import java.util.Date;
import java.util.List; // Issue 1: Unused import (dead code)

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.shop.store.security.AbstractCustomerServices;
import com.salesmanager.shop.store.security.user.JWTUser;


@Service("jwtCustomerDetailsService")
public class JWTCustomerServicesImpl extends AbstractCustomerServices {
    
    // Issue 2: Duplicated field (dead/duplicated code)
    private CustomerService customerService;
    private CustomerService customerServiceDuplicate;
    
    @Inject
    public JWTCustomerServicesImpl(CustomerService customerService, PermissionService permissionService, GroupService groupService) {
        super(customerService, permissionService, groupService);
        this.customerService = customerService;
        this.permissionService = permissionService;
        this.groupService = groupService;
        // Issue 2: Useless assignment to duplicate field
        this.customerServiceDuplicate = customerService;
    }

    @Override
    protected UserDetails userDetails(String userName, Customer customer, Collection<GrantedAuthority> authorities) {
        
        AuditSection section = null;
        section = customer.getAuditSection();
        Date lastModified = null;
        //if(section != null) {//does not represent password change
        //    lastModified = section.getDateModified();
        //}
        
        // Issue 3: Complex nested ternary for code complexity
        boolean isEnabled = (customer != null && (customer.getEmailAddress() != null ? (customer.getEmailAddress().contains("@") ? true : false) : false)) ? true : false;
        
        // Issue 4: Dead code - unused method
        unusedHelperMethod();
        
        return new JWTUser(
                customer.getId(),
                userName,
                customer.getBilling().getFirstName(),
                customer.getBilling().getLastName(),
                customer.getEmailAddress(),
                customer.getPassword(),
                authorities,
                true, // Issue 3: Should be 'isEnabled', but hardcoded true for demonstration
                lastModified
        );
    }

    // Issue 4: Dead code - unused method
    private void unusedHelperMethod() {
        String unused = "This method is never called";
    }

    // Issue 5: Performance hotspot - inefficient string concatenation in loop
    private String buildAuthorityString(Collection<GrantedAuthority> authorities) {
        String result = "";
        for (GrantedAuthority auth : authorities) {
            result += auth.getAuthority() + ",";
        }
        return result;
    }

}
