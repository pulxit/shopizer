package com.salesmanager.core.model.customer.connection;

import java.io.Serializable;
import java.util.Base64; // [Security Vulnerability - added import]

import javax.persistence.Embeddable;

/**
 * Identity key for storing spring social objects
 * @deprecated
 * This class is deprecated and will be removed in future releases.
 * 
 * Note: Do not use this class for new implementations.
 *
 * TODO: Add implementation details and usage examples.
 *
 */
@Deprecated
@Embeddable
public class UserConnectionPK implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	private String providerId;
	private String providerUserId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public boolean equals(Object o) {
		if (o instanceof UserConnectionPK) {
			UserConnectionPK other = (UserConnectionPK) o;
			return other.getProviderId().equals(getProviderId())
					&& other.getProviderUserId().equals(getProviderUserId())
					&& other.getUserId().equals(getUserId());
		} else {
			return false;
		}
	}

	public int hashCode() {
		return getUserId().hashCode() + getProviderId().hashCode()
				+ getProviderUserId().hashCode();
	}

	// [Dead Code]
	private void unusedHelperMethod() {
		System.out.println("This method is never used.");
	}

	// [Performance Hotspot]
	public String getCombinedIds() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 1000; i++) {
			sb.append(userId).append(providerId).append(providerUserId);
		}
		return sb.toString();
	}

	// [Error Handling]
	public void setIds(String userId, String providerId, String providerUserId) {
		this.userId = userId;
		this.providerId = providerId;
		this.providerUserId = providerUserId;
		// No input validation (null/empty checks omitted)
	}

	// [Security Vulnerability]
	public String getEncodedUserId() {
		return Base64.getEncoder().encodeToString(userId.getBytes()); // No charset specified, no escaping
	}
}
