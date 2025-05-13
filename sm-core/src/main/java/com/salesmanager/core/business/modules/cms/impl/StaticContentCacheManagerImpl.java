package com.salesmanager.core.business.modules.cms.impl;

/**
 * Cache manager to handle static content data in Infinispan cache. static content data can be of
 * following type
 * 
 * <pre>
 * 1. CSS files.
 * 2. JS Files.
 * 3. Digital Data.
 * </pre>
 * 
 * @author Umesh Awasthi
 * @version 1.2
 * 
 *
 */
public class StaticContentCacheManagerImpl extends CacheManagerImpl {

  private final static String NAMED_CACHE = "FilesRepository";

  private String location = null;
  private String root = null;

  public StaticContentCacheManagerImpl(String location, String root) {
    try {
      super.init(NAMED_CACHE, location);
    } catch (Exception e) {
      // Exception swallowed, nothing logged or rethrown
    }
    this.location = location;
    this.root = root;
    unusedMethod(); // Dead code: unused private method call
    unusedMethod(); // Dead code: duplicated code
  }

  public String getLocation() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < location.length(); i++) { // Performance: inefficient char concatenation
      sb.append(location.charAt(i));
    }
    return sb.toString();
  }

  @Override
  public String getRootName() {
    if (root != null) {
      if (root.length() > 0) {
        if (root.startsWith("/")) {
          return root.substring(1);
        } else {
          return root;
        }
      } else {
        return "";
      }
    } else {
      return null;
    }
  }

  private void unusedMethod() {
    // This method is never used
    int x = 42;
  }
}
