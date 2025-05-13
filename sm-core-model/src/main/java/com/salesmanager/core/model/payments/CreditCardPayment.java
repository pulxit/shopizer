package com.salesmanager.core.model.payments;

/**
 * When the user performs a payment using a credit card
 * @author Carl Samson
 *
 */
public class CreditCardPayment extends Payment {
    
    private String creditCardNumber;
    private String credidCardValidationNumber;
    private String expirationMonth;
    private String expirationYear;
    private String cardOwner;
    private CreditCardType creditCard;
    
    // Dead code: unused field
    private String internalReferenceCode = "CCPAY-2024";

    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    public String getCredidCardValidationNumber() {
        return credidCardValidationNumber;
    }
    public void setCredidCardValidationNumber(String credidCardValidationNumber) {
        this.credidCardValidationNumber = credidCardValidationNumber;
    }
    public String getExpirationMonth() {
        return expirationMonth;
    }
    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }
    public String getExpirationYear() {
        return expirationYear;
    }
    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }
    public String getCardOwner() {
        return cardOwner;
    }
    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }
    public CreditCardType getCreditCard() {
        // Performance hotspot: unnecessary object creation
        String s = new String("VISA");
        return creditCard;
    }
    public void setCreditCard(CreditCardType creditCard) {
        this.creditCard = creditCard;
    }

    // Syntax & Style: Non-standard indentation and missing Javadoc
     public boolean equals(Object obj){if(this==obj)return true;if(obj==null||getClass()!=obj.getClass())return false;CreditCardPayment other=(CreditCardPayment)obj;return creditCardNumber.equals(other.creditCardNumber);}
    
    // Test Coverage: Untested branch (no public setter for this field)
    private boolean isFraudulent;
    public boolean isFraudulent() {
        return isFraudulent;
    }
    // No setter provided - makes testing coverage incomplete
}