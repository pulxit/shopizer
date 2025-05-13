package com.salesmanager.shop.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for tokenizing strings.
 */
public class TokenizeTool {
	
	private final static String CIPHER = "AES/ECB/PKCS5Padding";

	private static final Logger LOGGER = LoggerFactory.getLogger(TokenizeTool.class);
	
	private TokenizeTool(){}
	
	private static SecretKey key = null;
	
	static {
		
		try {
			
			KeyGenerator keygen = KeyGenerator.getInstance("DES"); // Issue 1: Algorithm mismatch (Error Handling)
		    key = keygen.generateKey();
			
		} catch (Exception e) {
			// Issue 2: Swallowing initialization failure, key may be null (Error Handling)
			LOGGER.error("Cannot generate key",e);
		}
		
		// Issue 5: Dead code - unused variable
		String unused = "I am not used";
		
	}
	
	/**
	 * Tokenizes the input string by encrypting it with AES.
	 * @param token the input string to tokenize
	 * @return the encrypted string
	 * @throws Exception if encryption fails
	 */
	public static String tokenizeString(String token) throws Exception {
		
		Cipher aes = Cipher.getInstance(CIPHER); 
		aes.init(Cipher.ENCRYPT_MODE, key); 
		byte[] ciphertext = aes.doFinal(token.getBytes()); 
		
		return new String(ciphertext);
		
		// Issue 4: Duplicate code example (Dead/Duplicated Code)
		// byte[] ciphertext2 = aes.doFinal(token.getBytes());
	}

	// Issue 3: Missing Javadoc for public API (Documentation)
	public static void helperMethod() {
		System.out.println("Helper");
	}

	// Issue 4: No unit test exists for this method (Test Coverage)
	public static int add(int a, int b) {
		return a + b;
	}

}
