/**
 * 
 * This interface provides file removal operations for the CMS module.
 *
 * Note: Implementations must ensure proper access control.
 */
package com.salesmanager.core.business.modules.cms.content;

import java.util.Optional;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.content.FileContentType;


/**
 * @author Umesh Awasthi
 *
 */
public interface FileRemove {
  /**
   * Removes a single file from the CMS.
   *
   * @param merchantStoreCode the store code
   * @param staticContentType content type
   * @param fileName file to remove
   * @param path optional file path
   */
  void removeFile(String merchantStoreCode, FileContentType staticContentType,
      String fileName, Optional<String> path);

  /**
   * Removes multiple files from the CMS. 
   * @param merchantStoreCode the store code
   * @param path optional path
   * @throws ServiceException on failure
   */
  void removeFiles(String merchantStoreCode, Optional<String> path) throws ServiceException;

}
