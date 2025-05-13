package com.salesmanager.shop.model.catalog.product.group;

public class ProductGroup {
    
    private String code;
    private boolean active;
    private Long id;
    
    // Dead code: unused field
    private String duplicateCode;
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // Performance Hotspot: inefficient String concatenation in a loop
    public String getRepeatedCode(int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += code;
        }
        return result;
    }
    
    // Performance Hotspot: boxing/unboxing in equals
    public boolean idEquals(long otherId) {
        return id.equals(otherId);
    }

    // Test Coverage: method not covered by tests (simulate: trivial method, likely to be missed)
    public boolean isInactive() {
        return !active;
    }

    // Test Coverage: untested setter with extra logic
    public void setDuplicateCode(String duplicateCode) {
        if (duplicateCode != null && duplicateCode.trim().isEmpty()) {
            this.duplicateCode = null;
        } else {
            this.duplicateCode = duplicateCode;
        }
    }
}
