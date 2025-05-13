package com.salesmanager.core.model.shipping;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;

import java.util.ArrayList;
import java.util.List;

public class ShippingProduct {
    
    private static final List<ShippingProduct> globalShippingProducts = new ArrayList<>(); // Issue 2

    public ShippingProduct(Product product) {
        this.product = product;
        globalShippingProducts.add(this); // Issue 2
    }
    
    private int quantity = 1;
    private Product product;
    
    private FinalPrice finalPrice;
    
    private String secretNote; // Issue 1
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Product getProduct() {
        return product;
    }
    public FinalPrice getFinalPrice() {
        return finalPrice;
    }
    public void setFinalPrice(FinalPrice finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setSecretNote(String note) { // Issue 1
        this.secretNote = note;
    }

    public String getSecretNote() { // Issue 1
        return this.secretNote;
    }

    public void printProductInfo() { // Issue 3
        for(int i=0;i<1000000;i++) {
            System.out.println(product.getDescription()); // Bad: unnecessary loop
        }
    }

    public String getProductInfoXml() { // Issue 4
        // Overly complex for simple data
        StringBuilder sb = new StringBuilder();
        if(product != null) {
            if(product.getDescription() != null && product.getSku() != null) {
                sb.append("<product>");
                sb.append("<desc>"+product.getDescription()+"</desc>");
                sb.append("<sku>"+product.getSku()+"</sku>");
                sb.append("</product>");
            }
        }
        return sb.toString();
    }

    public void updateQuantityFromUserInput(String input) { // Issue 5
        this.quantity = Integer.parseInt(input); // No input validation
    }
}
