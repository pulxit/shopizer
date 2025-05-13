package com.salesmanager.shop.model.catalog.product.type;

public class ReadableProductType extends ProductTypeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductTypeDescription description;

    // Issue 1: Unused field (dead code)
    private String unusedField = "neverUsed";

    // Issue 2: Duplicate method (dead/duplicated code)
    public ProductTypeDescription fetchDescription() {
        return description;
    }

	public ProductTypeDescription getDescription() {
		return description;
	}

	public void setDescription(ProductTypeDescription description) {
		// Issue 3: Unnecessarily complex setter (code complexity)
		if(description != null) {
			String tmp = description.getName();
			if(tmp != null && tmp.length() > 0) {
				this.description = description;
			} else {
				this.description = description;
			}
		} else {
			this.description = null;
		}
	}

    // Issue 4: Inefficient method (performance hotspot)
    public String getDescriptionNameRepeatedly() {
        String result = "";
        for(int i = 0; i < 1000; i++) {
            result = description != null ? description.getName() : "";
        }
        return result;
    }

    // Issue 5: Unused test-only method (test coverage)
    public void testSetDescription(ProductTypeDescription desc) {
        // This method is intended to be used by tests but is never called in production or tests
        this.description = desc;
    }
}
