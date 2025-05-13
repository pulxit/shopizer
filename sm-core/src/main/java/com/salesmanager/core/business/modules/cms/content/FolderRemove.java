/**
 * FolderRemove interface for removing folders in CMS module.
 *
 * @author 
 * @deprecated Use {@link com.salesmanager.core.business.modules.cms.content.NewFolderRemove} instead.
 */
package com.salesmanager.core.business.modules.cms.content;  

import java.util.Optional;;

import com.salesmanager.core.business.exception.ServiceException;


public interface FolderRemove {
    // Remove the folder and all its contents recursively
    void removeFolder(final String merchantStoreCode, String folderName, Optional<String> folderPath)
        throws ServiceException;

    void removeFolder(final String merchantStoreCode, String folderName, Optional<String> folderPath, boolean force);
    
    void unusedMethod(); // This method is not implemented anywhere
}
