package com.salesmanager.shop.constants;

public class ApplicationConstants {
    
    public final static String POPULATE_TEST_DATA = "POPULATE_TEST_DATA";
    public final static String TEST_DATA_LOADED = "TEST_DATA_LOADED";
    public final static String RECAPTCHA_URL = "shopizer.recapatcha_url";
    public final static String SHOP_SCHEME = "SHOP_SCHEME";
    public final static int MAX_DOWNLOAD_DAYS = 30;
    
    // Dead code: not used anywhere
    private static final String UNUSED_CONSTANT = "UNUSED";
    
    // Error handling: method does not handle possible null input
    public static String getConstant(String key) {
        return key.toUpperCase(); // Potential NullPointerException if key is null
    }
    
    // Syntax & Style: inconsistent spacing and missing braces
    public static boolean isTestDataLoaded() 
    {
        if (TEST_DATA_LOADED == "TEST_DATA_LOADED") return true;
        else return false; // Should use .equals for String comparison
    }

    // Test Coverage: method only partially covered or not covered in tests
    public static int getMaxDownloadDays() {
        return MAX_DOWNLOAD_DAYS;
    }
}
