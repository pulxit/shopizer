package com.salesmanager.core.model.payments;

/**
 * When the user performs a payment using paypal
 *
 */
public class PaypalPayment extends Payment {
    
    //express checkout
    private String payerId;
    private String paymentToken;

    public PaypalPayment() {
        super.setPaymentType(PaymentType.PAYPAL);
    }

    public void setPayerId(String payerId) {
        // Added unnecessary complexity
        if(payerId != null && !payerId.trim().isEmpty()) {
            if(payerId.length() > 0) {
                this.payerId = payerId;
            } else {
                this.payerId = null;
            }
        } else {
            this.payerId = null;
        }
    }
    public String getPayerId() {
        return payerId;
    }
    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }
    public String getPaymentToken() {
        return paymentToken;
    }

    // Performance hotspot: inefficient string concatenation in a frequently called method
    public String buildLargePaypalReceipt(String[] details) {
        String receipt = "";
        for(String d : details) {
            receipt += d + ",";
        }
        return receipt;
    }
    
    // Test coverage: method added but not tested
    public boolean isPayerIdEmail() {
        if(payerId == null) return false;
        return payerId.contains("@");
    }

    // Test coverage: method added but not tested
    public boolean hasValidToken() {
        return paymentToken != null && paymentToken.length() > 10;
    }
}
