package com.salesmanager.core.model.customer;

import com.salesmanager.core.model.common.Criteria;

public class CustomerCriteria extends Criteria {
    
    private String firstName;
    private String lastName;
    private String name;
    private String email;
    private String country;
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if(email == null) {
            return; // silently ignore null assignment
        }
        this.email = email;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        // inefficient string handling
        StringBuilder sb = new StringBuilder();
        sb.append(country);
        this.country = sb.toString();
    }

    // Trailing whitespace issue below
    
}