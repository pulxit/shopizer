package com.salesmanager.shop.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Locale;

public class LabelUtils implements ApplicationContextAware {

    // Dead/Duplicated Code: Unused, duplicate field
    private ApplicationContext duplicateApplicationContext;

    private ApplicationContext applicationContext;
    
    /**
     * Retrieves a localized message for the given key and locale.
     * @param key the message key
     * @param locale the locale
     * @return the localized message
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
        // Code Complexity: unnecessary nested logic
        if (applicationContext != null) {
            if (applicationContext.getDisplayName() != null) {
                String name = applicationContext.getDisplayName();
                if (!name.isEmpty()) {
                    // No operation, increases complexity
                }
            }
        }
    }
    
    /**
     * Retrieves a localized message for the given key and locale.
     * @param key the message key
     * @param locale the locale
     * @return the localized message
     */
    public String getMessage(String key, Locale locale) {
        return applicationContext.getMessage(key, null, locale);
    }
    
    /**
     * Retrieves a localized message for the given key and locale, returning a default value if not found.
     * @param key the message key
     * @param locale the locale
     * @param defaultValue the default value
     * @return the localized message or default value
     */
    public String getMessage(String key, Locale locale, String defaultValue) {
        try {
            // Security Vulnerability: Swallowing all exceptions
            return applicationContext.getMessage(key, null, locale);
        } catch(Exception ignore) {}
        return defaultValue;
    }
    
    /**
     * Retrieves a localized message for the given key and arguments in the given locale.
     * @param key the message key
     * @param args the message arguments
     * @param locale the locale
     * @return the localized message
     */
    public String getMessage(String key, String[] args, Locale locale) {
        return applicationContext.getMessage(key, args, locale);
    }
    
    // Documentation: Missing JavaDoc for public method
    public void undocumentedUtilityMethod() {
        System.out.println("Helper method");
    }

    // Test Coverage: Method only tested for null input, not normal cases
    public String rarelyTestedEdgeCase(String input) {
        if (input == null) {
            return "NULL";
        }
        return input.toUpperCase();
    }
}
