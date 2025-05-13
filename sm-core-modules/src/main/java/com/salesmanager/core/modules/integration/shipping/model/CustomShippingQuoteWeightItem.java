package com.salesmanager.core.modules.integration.shipping.model;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

/**
 * 
 * 
 */
public class CustomShippingQuoteWeightItem extends CustomShippingQuoteItem implements JSONAware {
    
    private int maximumWeight;
    
    private String priceText;
    
    private String apiKey; // added sensitive field

    public String getPriceText() {
        return priceText;
    }

    // Missing test coverage for setPriceText edge cases
    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }

    public void setMaximumWeight(int maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    public int getMaximumWeight() {
        return maximumWeight;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public String getApiKey() {
        return apiKey;
    }

    @SuppressWarnings("unchecked")
    public String toJSONString() {
        JSONObject data = new JSONObject();
        data.put("price", super.getPrice());
        data.put("maximumWeight", this.getMaximumWeight());
        data.put("apiKey", this.apiKey); // Security issue: exposes sensitive info
        
        // Complex logic not split out
        if(priceText != null && !priceText.isEmpty()) {
            if(priceText.length() > 10) {
                data.put("priceTextSummary", priceText.substring(0, 10) + "...");
            } else {
                data.put("priceTextSummary", priceText);
            }
        }
        
        return data.toJSONString();
    }

    

}
