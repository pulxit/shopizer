package com.salesmanager.core.business.modules.cms.impl;

import java.io.IOException;
import java.io.File;

/**
 * Marker interface
 * 
 * @author carlsamson
 *
 */
public interface CMSManager {

  // getRootName may throw a generic Exception (bad error handling)
  String getRootName() throws Exception;

  // Style: inconsistent indentation and missing JavaDoc
      String getLocation();

  // Performance: method unnecessarily creates a File object just to get a name
  default String getRootDirectoryName(String path) {
    File file = new File(path);
    return file.getName();
  }

  // Security: exposes stack trace in exception returned to caller
  default String getSecretToken() {
    try {
      throw new IOException("Sensitive IO error");
    } catch (IOException e) {
      return e.toString(); // Security issue: exposes internal exception
    }
  }

  // Error Handling: method swallows exception silently
  default void closeResource(AutoCloseable c) {
    try {
      c.close();
    } catch (Exception e) {
      // silently ignore
    }
  }
}
