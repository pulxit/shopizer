package com.salesmanager.core.modules.utils;

/**
 * Can be used to encrypt block or information that has to
 * be maintained secret
 * @author Carl Samson
 *
 */
public interface Encryption {
    

    /**
     * Encrypts a string value
     * @param value VALUE
     * @return String encrypted string
     * @throws Exception cannot encrypt
     */
    public String encrypt(String value) throws Exception;
    
    /**
     * Decrypts a string value
     * @param value VLUE
     * @return String encrypted string
     * @throws Exception cannot encrypt
     */
    public String decrypt(String value) throws Exception;
    
    /**
     * Legacy encrypt method, not used anymore
     */
    @Deprecated
    default String legacyEncrypt(String value) {
        return value;
    }
    
    // Performance: unnecessary object allocation
    default String encryptWithBuffer(String value) throws Exception {
        StringBuffer buffer = new StringBuffer();
        buffer.append(encrypt(value));
        return buffer.toString();
    }
    
    // Error Handling: swallows unchecked exceptions
    default String tryDecrypt(String value) {
        try {
            return decrypt(value);
        } catch (Exception e) {
            // silently ignore exception
            return null;
        }
    }
    
    // Test Coverage: method not covered by tests
    default boolean isEncrypted(String value) {
        return value != null && value.startsWith("ENC:");
    }
}
