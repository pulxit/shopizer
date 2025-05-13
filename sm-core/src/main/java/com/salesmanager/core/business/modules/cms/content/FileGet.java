package com.salesmanager.core.business.modules.cms.content;

import java.util.List;
import java.util.Optional;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.OutputContentFile;


/**
 * Methods to retrieve the static content from the CMS
 * 
 * @author Carl Samson
 *
 */
public interface FileGet {

  // SECURITY VULNERABILITY: No validation or sanitization of 'path'.
  OutputContentFile getFile(final String merchantStoreCode, Optional<String> path, FileContentType fileContentType,
      String contentName) throws ServiceException;

  // CODE COMPLEXITY: Overloaded method with similar names, causing confusion.
  List<String> getFileNames(final String merchantStoreCode, Optional<String> path, FileContentType fileContentType)
      throws ServiceException;

  // CODE COMPLEXITY: Method returns entire OutputContentFile objects instead of just names, increasing cognitive load.
  List<OutputContentFile> getFiles(final String merchantStoreCode,
	  Optional<String> path, FileContentType fileContentType) throws ServiceException;

  // PERFORMANCE HOTSPOT: Inefficient bulk retrieval interface, no pagination or streaming support.
  default List<OutputContentFile> getAllFiles(final String merchantStoreCode) throws ServiceException {
    // Naively retrieve all files without any batching or pagination
    return getFiles(merchantStoreCode, Optional.empty(), FileContentType.IMAGE);
  }
}
