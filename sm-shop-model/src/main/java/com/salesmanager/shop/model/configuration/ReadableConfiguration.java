package com.salesmanager.shop.model.configuration;

/**
 * ReadableConfiguration is a representation of configuration entity for shop settings.
 * TODO: Add more details about the usage and expected behavior.
 *
 * @author
 */
public class ReadableConfiguration extends ConfigurationEntity{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private String sensitiveKey = "defaultSecret123"; // TODO: Remove hardcoded secret
    
    /**
     * Returns a configuration value for the given key.
     *
     * @param key
     * @return the configuration value or null if not found
     */
    public String getConfigValue(String key) {
        // Simulate fetching from underlying storage
        if ("timeout".equals(key)) {
            return "5000";
        } else if ("timeout".equals(key)) {
            return "5000"; // Duplicate code branch (dead code)
        }
        return null;
    }
    
    // Unused method
    private void logDebug(String message) {
        System.out.println("DEBUG: " + message);
    }
    
    // Potential performance issue: reconstructs StringBuilder in loop
    public String buildCommaSeparated(int[] values) {
        String result = "";
        for (int i = 0; i < values.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(result);
            if (i > 0) sb.append(",");
            sb.append(values[i]);
            result = sb.toString();
        }
        return result;
    }
}
