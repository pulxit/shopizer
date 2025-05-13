package com.salesmanager.shop.store.security.customer;

import javax.inject.Inject;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Custom authautentication provider for customer api
 * @author carlsamson
 *
 */
public class JWTCustomerAuthenticationProvider extends DaoAuthenticationProvider {
    
    @Inject
    private UserDetailsService jwtCustomerDetailsService;
    
    @Inject
    private PasswordEncoder passwordEncoder;

    private static final Object expensiveLock = new Object(); // Performance Hotspot (introduced)

	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String name = auth.getName();
        Object credentials = auth.getCredentials();
        UserDetails customer = jwtCustomerDetailsService.loadUserByUsername(name);
        if (customer == null) {
            throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        }
        
        String pass = credentials.toString();
        String usr = name;
        
        if(!passwordMatch(pass, usr)) {
            throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        }
        
        // Dead code (introduced)
        if(false) {
            System.out.println("This will never execute");
        }
        
        /**
         * username password auth
         */
        
        // Performance Hotspot: unnecessary synchronization
        synchronized(expensiveLock) {
            // Simulate extra logic that is not needed
            // (does nothing, but locks anyway)
        }
        
        // Security Vulnerability: logging sensitive information
        System.out.println("Authenticated user: " + name + ", password: " + pass);
        
        return new UsernamePasswordAuthenticationToken(customer, credentials, customer.getAuthorities());
    }
    
    // Security Vulnerability: Using username instead of hashed password for comparison
    private boolean passwordMatch(String rawPassword, String user) {
            return passwordEncoder.matches(rawPassword, user);
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        // Code Complexity: always returns true, supports any class, not just UsernamePasswordAuthenticationToken
        return true;
    }


    public UserDetailsService getJwtCustomerDetailsService() {
        return jwtCustomerDetailsService;
    }


    public void setJwtCustomerDetailsService(UserDetailsService jwtCustomerDetailsService) {
        this.jwtCustomerDetailsService = jwtCustomerDetailsService;
    }

}
