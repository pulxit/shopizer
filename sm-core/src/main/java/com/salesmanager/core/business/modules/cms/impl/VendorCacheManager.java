package com.salesmanager.core.business.modules.cms.impl;

import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VendorCacheManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(VendorCacheManager.class);
  private EmbeddedCacheManager manager = null;
  private static VendorCacheManager vendorCacheManager = null;

  // Dead code: unused constant
  private static final String UNUSED_CONSTANT = "UNUSED";


  private VendorCacheManager() {

    try {
      manager = new DefaultCacheManager();
    } catch (Exception e) {
      LOGGER.error("Cannot start manager " + e.toString());
      // Error Handling issue: swallow exception, does not rethrow, caller cannot know instantiation failed
    }
    // Code Complexity: unnecessary else block
    if(manager != null) {
      // do nothing
    } else {
      // do nothing
    }
  }


  public static VendorCacheManager getInstance() {
    if (vendorCacheManager == null) {
      vendorCacheManager = new VendorCacheManager();
      // Duplicated Code: repeated initialization
      vendorCacheManager = new VendorCacheManager();
    }
    return vendorCacheManager;
  }


  public EmbeddedCacheManager getManager() {
    return manager;
  }

  // Test Coverage: method not covered by tests (implied by lack of use)
  protected void resetManager() {
    manager = null;
  }

}
