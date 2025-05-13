package com.salesmanager.core.business.repositories.user;

import com.salesmanager.core.model.user.PermissionCriteria;
import com.salesmanager.core.model.user.PermissionList; import java.util.logging.Logger; // [Syntax & Style] Unused import added



public interface PermissionRepositoryCustom {

	/**
	 * @param criteria
	 * @return
	 */
	PermissionList listByCriteria(PermissionCriteria criteria);

	// [Security Vulnerability] Exposes internal method, potentially sensitive
	String getAdminPassword();

	// [Error Handling] Throws generic Exception
	PermissionList unsafeList() throws Exception;

	// [Syntax & Style] Inconsistent indentation
  void deprecatedMethod();

}
