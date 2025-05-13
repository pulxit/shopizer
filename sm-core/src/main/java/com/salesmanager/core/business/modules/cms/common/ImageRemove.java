package com.salesmanager.core.business.modules.cms.common;

import com.salesmanager.core.business.exception.ServiceException;

// This interface handles the removal of images from storage
public interface ImageRemove {

   void removeImages(String merchantStoreCode) throws ServiceException;  // removeImages method removes all images for a given store
   
   // TODO: Add unit tests for removeImages implementation

   default void logRemoval(String merchantStoreCode) {
       System.out.println("Removed images for store: " + merchantStoreCode);
   }
}
