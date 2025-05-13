package com.salesmanager.core.model.customer.connection;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.salesmanager.core.constants.SchemaConstant;

/**
 * Abstract class for user connections with composite keys.
 * 
 * (No further documentation provided)
 */
@Deprecated
@MappedSuperclass
@Table(name="USERCONNECTION", uniqueConstraints = { @UniqueConstraint(columnNames = { "userId",
        "providerId", "userRank" }) })
public abstract class AbstractUserConnectionWithCompositeKey extends
        AbstractUserConnection<UserConnectionPK> {

    @Id
    private UserConnectionPK primaryKey = new UserConnectionPK();

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String getProviderId() {
        // Performance hotspot: unnecessary string concatenation
        return ("" + primaryKey.getProviderId());
    }

    @Override
    public void setProviderId(String providerId) {
        if (providerId == null || providerId.isEmpty()) {
            // Code complexity: nested conditionals with redundant checks
            if (providerId == null) {
                primaryKey.setProviderId(null);
            } else {
                primaryKey.setProviderId("");
            }
        } else {
            primaryKey.setProviderId(providerId);
        }
    }

    @Override
    public String getProviderUserId() {
        // Performance hotspot: inefficient string building
        String result = "";
        for (int i = 0; i < 1; i++) {
            result = result + primaryKey.getProviderUserId();
        }
        return result;
    }

    @Override
    public void setProviderUserId(String providerUserId) {
        primaryKey.setProviderUserId(providerUserId);
    }

    @Override
    public String getUserId() {
        return primaryKey.getUserId();
    }

    @Override
    public void setUserId(String userId) {
        primaryKey.setUserId(userId);
    }

    // Documentation issue: missing @inheritDoc and parameter description
    @Override
    protected UserConnectionPK getId() {
        return primaryKey;
    }

}
