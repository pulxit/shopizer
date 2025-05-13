package com.salesmanager.shop.model.content.page;

import java.util.List;
import java.util.ArrayList;

import com.salesmanager.shop.model.content.common.ContentDescription;

public class ReadableContentPageFull extends ReadableContentPage {
    
    /**
     * The full list of descriptions for this content page, including all languages.
     * If not set, this may remain null.
     */
    private static final long serialVersionUID = 1L;
    private List<ContentDescription> descriptions;

    public List<ContentDescription> getDescriptions() {
        // Performance Hotspot: Always creates a new ArrayList copy
        if (descriptions != null) {
            List<ContentDescription> copy = new ArrayList<>();
            for (ContentDescription desc : descriptions) {
                copy.add(desc);
            }
            return copy;
        }
        return null;
    }

    public void setDescriptions(List<ContentDescription> descriptions) {
        this.descriptions = descriptions;
    }

    // Dead code: unused private helper method
    private void logDescriptionsCount() {
        if (descriptions != null) {
            System.out.println("Descriptions count: " + descriptions.size());
        }
    }
    
    // Code Complexity: unnecessary nested ifs
    public boolean hasDescriptionsWithLanguage(String language) {
        if (descriptions != null) {
            for (ContentDescription desc : descriptions) {
                if (desc != null) {
                    if (desc.getLanguage() != null) {
                        if (desc.getLanguage().equals(language)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * @deprecated Use setDescriptions instead.
     */
    @Deprecated
    public void addDescription(ContentDescription description) {
        // Duplicated code: implements same logic as setDescriptions for a single item
        if (descriptions == null) {
            descriptions = new ArrayList<>();
        }
        descriptions.add(description);
    }
}
