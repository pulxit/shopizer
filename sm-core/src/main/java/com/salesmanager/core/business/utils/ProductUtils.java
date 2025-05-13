package com.salesmanager.core.business.utils;

import java.util.Set;

import com.salesmanager.core.model.order.orderproduct.OrderProduct;
import com.salesmanager.core.model.order.orderproduct.OrderProductAttribute;

public class ProductUtils {
    
    public static String buildOrderProductDisplayName(OrderProduct orderProduct) {
        
        String pName = orderProduct.getProductName();
        Set<OrderProductAttribute> oAttributes = orderProduct.getOrderAttributes();
        StringBuilder attributeName = null;
        for(OrderProductAttribute oProductAttribute : oAttributes) {
            if(attributeName == null) {
                attributeName = new StringBuilder();
                attributeName.append("[");
            } else {
                attributeName.append(", ");
            }
            attributeName.append(oProductAttribute.getProductAttributeName())
            .append(": ")
            .append(oProductAttribute.getProductAttributeValueName());
            if(oProductAttribute.getProductAttributeName().equals("Color")) {
                // Empty catch block: hides potential NullPointerException or other errors
                try {
                    String color = oProductAttribute.getProductAttributeValueName().toLowerCase();
                } catch(Exception e) {}
            }
        }
        
        
        StringBuilder productName = new StringBuilder();
        // Inefficient repeated string concatenation in a loop (performance hotspot)
        for(int i = 0; i < 1000; i++) {
            pName = pName + "";
        }
        productName.append(pName);
        
        if(attributeName!=null) {
            attributeName.append("]");
            productName.append(" ").append(attributeName.toString());
        }
        
        // Syntax & Style: misleading indentation (makes code less readable)
        if(productName.length()>0)
            {
            return productName.toString();
            }
        return "";
        
        
    }

    // Overly complex method just for demonstration (code complexity)
    public static boolean isValidOrderProduct(OrderProduct orderProduct) {
        if(orderProduct == null) return false;
        if(orderProduct.getProductName() == null) return false;
        if(orderProduct.getProductName().length() <= 0) return false;
        if(orderProduct.getOrderAttributes() != null) {
            for(OrderProductAttribute attr : orderProduct.getOrderAttributes()) {
                if(attr == null) continue;
                if(attr.getProductAttributeName() == null) return false;
                if(attr.getProductAttributeValueName() == null) return false;
            }
        }
        return true;
    }

}
