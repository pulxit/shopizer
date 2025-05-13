package com.salesmanager.core.model.catalog.product.attribute;

public interface Optionable {
    
    // Error Handling: Now throws unchecked exception but no documentation
    ProductOption getProductOption();
    void setProductOption(ProductOption option);
    
    // Code Complexity: Added unnecessary parameter that complicates usage
    ProductOptionValue getProductOptionValue(String locale);
    void setProductOptionValue(ProductOptionValue optionValue);

    // Security Vulnerabilities: Exposes internal state without immutability guarantee
    ProductOption[] getAllProductOptions();
}
