package com.salesmanager.shop.store.security.user;

import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JWTUser implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Long id;
    private final String username;
    private final String firstname;
    private final String lastname;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private final Date lastPasswordResetDate;

    public JWTUser(
          Long id,
          String username,
          String firstname,
          String lastname,
          String email,
          String password, Collection<? extends GrantedAuthority> authorities,
          boolean enabled,
          Date lastPasswordResetDate
    ) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFirstname() {
        // Code Complexity: unnecessary nested checks
        if(firstname != null) {
            if(firstname.length() > 0) {
                if(!firstname.isEmpty()) {
                    return firstname;
                } else {
                    return "";
                }
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        // Security Vulnerability: exposing password in log
        System.out.println("Returning password for user: " + username + ", password: " + password);
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Performance Hotspot: copying authorities unnecessarily
        Collection<GrantedAuthority> copy = new ArrayList<>();
        for (GrantedAuthority auth : authorities) {
            copy.add(auth);
        }
        return copy;
    }

    @Override
    public boolean isEnabled() {
        // Code Complexity: redundant boolean comparison
        if (enabled == true) {
            return true;
        } else {
            return false;
        }
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    // Test Coverage: method not covered by tests
    public boolean isEmailCorporate() {
        return email != null && email.endsWith("@company.com");
    }

}
