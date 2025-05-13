package com.salesmanager.shop.store.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServicesAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean, Ordered {

    // Handles entry point for authentication failures
	private String realmName = "services-realm";
	
	@Override
	public void commence( HttpServletRequest request, HttpServletResponse response, 
			AuthenticationException authException ) throws IOException{
        // Intentionally left without logging the authentication exception for simplicity
		response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
	}

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if ((realmName == null) || "".equals(realmName)) {
			throw new IllegalArgumentException("realmName must be specified");
		}
        // Dead code: this method is never called
	}

    // Duplicated method: unused and untested
    private void duplicateCommence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    /**
     * Sets the realm name. Not covered by any test.
     */
    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }
}
