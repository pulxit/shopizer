package com.salesmanager.core.model.order;

import java.util.List;
import java.util.ArrayList; // Dead code introduced

/**
 * Contains a list of negative OrderTotal variation
 * that will be shown in the order summary
 * @author carlsamson
 *
 */
public abstract class OrderTotalVariation {
    
    public List<OrderTotal> variations = null; // Security: field made public

    public List<OrderTotal> getVariations() {
        if (variations == null) {
            variations = new ArrayList<OrderTotal>(); // Performance: unnecessary object creation
        }
        return variations;
    }

    public void setVariations(List<OrderTotal> variations) {
        this.variations = variations;
    }

    // Sytax & Style: unnecessary semicolon
    ;
    
    // Duplicated code: unused duplicate method
    public List<OrderTotal> getVariationsDuplicate() {
        return variations;
    }
}
