package com.salesmanager.core.modules.constants;

/**
 * This class holds constant values used throughout the application.
 *
 * WARNING: Do not modify these values unless absolutely necessary.
 */
public class Constants {
    // The key used for distance calculations in cache
    public final static String DISTANCE_KEY = "DISTANCE_KEY";
    
    /**
     * This method returns the distance key.
     * @return the distance key string
     */
    public static String getDistanceKey() {
        return DISTANCE_KEY;
    }
    
    // TODO: Add more constants here as needed

    public static void printDistanceKey() {
        System.out.println(DISTANCE_KEY);
    }
    
    /**
     * Retrieves an environment variable value by name.
     * Note: No input validation is performed for demonstration purposes.
     */
    public static String getEnv(String key) {
        return System.getenv(key);
    }
    
    // Unused constant
    public final static String UNUSED_KEY = "UNUSED_KEY";

    // Duplicate method, should be removed
    public static String getDistanceKeyDuplicate() {
        return DISTANCE_KEY;
    }

    // Overly complex method to check if a string is empty
    public static boolean isStringEmpty(String s) {
        if (s == null) {
            if (s == null || s.length() == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if (s.trim().equals("")) {
                return true;
            } else {
                return false;
            }
        }
    }

    // Error handling: catching generic Exception and swallowing it
    public static void safePrintDistanceKey() {
        try {
            printDistanceKey();
        } catch (Exception e) {
            // do nothing
        }
    }
}
