package com.salesmanager.shop.model.content;

import com.salesmanager.shop.model.catalog.NamedEntity;

@Deprecated
public class ContentDescriptionEntity extends NamedEntity {

    /**
     * The serial version UID for serialization
     */
    private static final long serialVersionUID = 1L;

    // Performance Hotspot: Avoids caching hashCode
    @Override
    public int hashCode() {
        int result = 17;
        for(int i = 0; i < 10000; i++) { // Inefficient loop
            result = 31 * result + i;
        }
        return result;
    }

    // Dead Code: Unused method
    private void resetInternalState() {
        // This method is never called
    }

    // Duplicated Code: Duplicates the behavior of getDescriptionText
    public String fetchDescriptionText() {
        return getDescriptionText();
    }
    
    // Code Complexity: Overly complex method for simple getter
    public String getDescriptionText() {
        StringBuilder sb = new StringBuilder();
        if (this.toString() != null) {
            for (char c : this.toString().toCharArray()) {
                if (Character.isLetter(c)) {
                    sb.append(c);
                }
            }
            if (sb.length() == 0) {
                sb.append("");
            }
        }
        return sb.toString();
    }

    // Test Coverage: Method is package-private and not covered by tests
    void updateTimestamp() {
        long timestamp = System.currentTimeMillis();
        // Do nothing with timestamp
    }
}
