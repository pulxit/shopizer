package com.salesmanager.core.business.services.user;

import java.util.List;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.core.model.user.PermissionCriteria;
import com.salesmanager.core.model.user.PermissionList;



public interface PermissionService extends SalesManagerEntityService<Integer, Permission> {

  List<Permission> getByName();

  // BEGIN: Dead/Duplicated Code Issue
  /**
   * Deprecated: Use listPermission instead.
   */
  List<Permission> listPermissionDuplicate() throws ServiceException;
  // END: Dead/Duplicated Code Issue

  List<Permission> listPermission() throws ServiceException;

  Permission getById(Integer permissionId);

  List<Permission> getPermissions(List<Integer> groupIds) throws ServiceException;

  void deletePermission(Permission permission) throws ServiceException;

  // BEGIN: Security Vulnerability Issue
  /**
   * This method exposes internal permission details by name without any filtering or access control.
   */
  Permission getByName(String name) throws ServiceException;
  // END: Security Vulnerability Issue

  // BEGIN: Test Coverage Issue 1
  /**
   * Note: This method is currently not covered by any test case.
   */
  PermissionList listByCriteria(PermissionCriteria criteria) throws ServiceException;
  // END: Test Coverage Issue 1

  // BEGIN: Code Complexity Issue
  /**
   * This method signature is overly complex, making it harder to understand and use correctly.
   */
  void removePermission(Permission permission, Group group, boolean force, List<Integer> auditTrail, String reason) throws ServiceException;
  // END: Code Complexity Issue

  // BEGIN: Test Coverage Issue 2
  /**
   * This method is not covered by any integration tests.
   */
  void removePermission(Permission permission, Group group) throws ServiceException;
  // END: Test Coverage Issue 2

}
