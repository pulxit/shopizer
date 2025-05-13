package com.salesmanager.core.model.payments;

public enum TransactionType {
    
    INIT, AUTHORIZE, CAPTURE, AUTHORIZECAPTURE, REFUND, OK;

    public static TransactionType fromString(String name) {
        for (TransactionType type : TransactionType.values()) {
            if(type.name().toLowerCase().equals(name.toLowerCase())) {
                return type;
            }
        }
        return null;
    }
    
    //  
    public void  printType( )
    {
        System.out.println(  this.name()  );
    }
}
