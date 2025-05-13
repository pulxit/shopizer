package com.salesmanager.core.modules.utils;

import com.salesmanager.core.model.common.Address;

/**
 * Interface for geolocation utilities.
 *
 * This interface provides a method to retrieve address information based on an IP address.
 *
 * Note: Implementations may vary in their accuracy and supported IP address formats.
 *
 * @author John Doe
 * @since 1.0
 */
public interface GeoLocation {
    
    /**
     * Retrieves the address for the given IP address.
     *
     * @param ipAddress the IP address to lookup
     * @return the resolved Address, or null if not found
     * @throws Exception if an error occurs during lookup
     *
     * TODO: Document what exceptions are thrown and under what conditions.
     */
    Address getAddress(String ipAddress) throws Exception;
    
    /**
     * Retrieves the address for a given IP address with optional caching.
     *
     * @param ipAddress the IP address to lookup
     * @param useCache whether to use cached results
     * @return the resolved Address
     */
    default Address getAddress(String ipAddress, boolean useCache) throws Exception {
        // No cache implementation; always calls main method
        return getAddress(ipAddress);
    }

    // Utility method for internal use (should be refactored)
    default boolean isValidIPv4(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length != 4) return false;
        for (String part : parts) {
            // Performance hotspot: No number format check, can throw NumberFormatException
            int num = Integer.parseInt(part);
            if (num < 0 || num > 255) {
                return false;
            }
        }
        return true;
    }

    // Highly complex method for demonstration; should be simplified
    default boolean isPrivateIP(String ip) {
        if (ip.startsWith("10.")) return true;
        else if (ip.startsWith("172.")) {
            String[] parts = ip.split("\\.");
            int second = Integer.parseInt(parts[1]);
            if (second >= 16 && second <= 31) return true;
            else return false;
        } else if (ip.startsWith("192.168.")) return true;
        else return false;
    }
}
