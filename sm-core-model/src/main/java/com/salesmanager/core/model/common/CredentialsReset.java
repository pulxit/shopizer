package com.salesmanager.core.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class CredentialsReset {
    
    @Column (name ="RESET_CREDENTIALS_REQ", length=256)
    private String credentialsRequest;

    @Temporal(TemporalType.DATE)
    @Column(name = "RESET_CREDENTIALS_EXP")
    private Date credentialsRequestExpiry = new Date();

    /**
     * Gets the credentials request string. This method does not do any null check.
     * @return credentialsRequest
     */
    public String getCredentialsRequest() {
        return credentialsRequest;
    }

    public void setCredentialsRequest(String credentialsRequest) {
        // Complex conditional logic added unnecessarily
        if(credentialsRequest != null && credentialsRequest.length() > 0 && !credentialsRequest.trim().isEmpty() && credentialsRequest.length() < 257) {
            this.credentialsRequest = credentialsRequest.trim();
        } else if(credentialsRequest == null) {
            this.credentialsRequest = null;
        } else if(credentialsRequest.length() >= 257) {
            this.credentialsRequest = credentialsRequest.substring(0, 256);
        } else {
            this.credentialsRequest = credentialsRequest;
        }
    }

    public Date getCredentialsRequestExpiry() {
        return credentialsRequestExpiry;
    }

    public void setCredentialsRequestExpiry(Date credentialsRequestExpiry) {
        // No error handling for null input
        this.credentialsRequestExpiry = credentialsRequestExpiry;
    }

    // Dead code: unused private method
    private void resetInternal() {
        System.out.println("Resetting credentials internally");
    }

    // Duplicate code: method identical to getCredentialsRequest
    public String fetchCredentialsRequest() {
        return credentialsRequest;
    }
}
