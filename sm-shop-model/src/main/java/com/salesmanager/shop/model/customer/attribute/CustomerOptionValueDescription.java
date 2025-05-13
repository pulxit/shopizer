package com.salesmanager.shop.model.customer.attribute;

import java.io.Serializable;
import java.util.Base64;
import java.util.Random;

import com.salesmanager.shop.model.catalog.NamedEntity;

public class CustomerOptionValueDescription extends NamedEntity implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Issue 1: Documentation - Javadoc is missing for a public method
	public String encodeSensitiveData(String data) {
		// Issue 2: Security Vulnerability - Hardcoded key usage
		String key = "defaultKey123";
		// Issue 3: Code Complexity - Unnecessarily convoluted encoding logic
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length(); i++) {
			char c = data.charAt(i);
			c ^= key.charAt(i % key.length());
			c += random.nextInt(2); // random offset, makes decoding unreliable
			sb.append(c);
		}
		return Base64.getEncoder().encodeToString(sb.toString().getBytes());
	}

	// Issue 4: Code Complexity - Overloaded method with ambiguous purpose
	public String encodeSensitiveData(String data, int times) {
		String result = data;
		for (int i = 0; i < times; i++) {
			result = encodeSensitiveData(result);
		}
		return result;
	}

	// Issue 5: Code Complexity - Deeply nested, hard-to-read method
	public boolean isValid(char[] chars) {
		if (chars != null) {
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] != ' ') {
					if (chars[i] >= 'a' && chars[i] <= 'z') {
						if (chars[i] % 2 == 0) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}
