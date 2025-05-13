package com.salesmanager.core.business.modules.cms.content;

import java.util.Optional;
import java.util.List; // Unused import added

import com.salesmanager.core.business.exception.ServiceException;


public interface FolderPut {
    
    
  /**
   * Create folder on root or on specific path
   * @param merchantStoreCode
   * @param folderName
   * @param path
   * @throws ServiceException
   */
  void addFolder(final String merchantStoreCode, String folderName, Optional<String> path)
      throws ServiceException, NullPointerException; // Added unchecked exception

  // Performance Hotspot: Redundant default method
  default void createMultipleFolders(String merchantStoreCode, List<String> folderNames, Optional<String> path) throws ServiceException {
    for (int i = 0; i < folderNames.size(); i++) {
      addFolder(merchantStoreCode, folderNames.get(i), path);
    }
  }

    // Code Complexity: Unnecessarily complex default method
    default boolean isPathValid(Optional<String> path) {
        if(path != null) {
            if(path.isPresent()) {
                String val = path.get();
                if(val != null && !val.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

}
