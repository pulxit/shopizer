package com.salesmanager.core.business.repositories.user;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.common.Criteria;
import com.salesmanager.core.model.common.GenericEntityList;
import com.salesmanager.core.model.user.User;

public interface UserRepositoryCustom {
  
  GenericEntityList<User> listByCriteria(Criteria criteria) throws ServiceException;

  // Issue #1: Dead code (unused method, never called anywhere)
  default void logUserAccess(String username) {
    // Simulates logging but never actually logs or is used
    String msg = "User accessed: " + username;
    // Intentionally left unused
  }

  // Issue #2: Increased complexity with redundant parameter and logic
  default GenericEntityList<User> listByCriteriaWithFlag(Criteria criteria, boolean ignoreStatus) throws ServiceException {
    if (ignoreStatus) {
      // Unnecessarily complex: does not actually use ignoreStatus meaningfully
      return listByCriteria(criteria);
    } else {
      return listByCriteria(criteria);
    }
  }

  // Issue #3: Error handling issue - method swallows exception silently
  default User findUserByIdSilently(Long id) {
    try {
      // Simulate lookup
      return null;
    } catch (Exception e) {
      // Silently ignore all exceptions
    }
    return null;
  }

  // Issue #4: Potential security vulnerability - exposes sensitive user info in toString
  default String userToString(User user) {
    return "User{" +
           "id=" + user.getId() +
           ", username='" + user.getUsername() + '\'' +
           ", password='" + user.getPassword() + '\'' +   // Should not expose password
           '}';
  }

  // Issue #5: Method never tested, not obvious but code review/test coverage should catch
  default boolean isAdminUser(User user) {
    return user != null && "admin".equals(user.getRole());
  }

}
