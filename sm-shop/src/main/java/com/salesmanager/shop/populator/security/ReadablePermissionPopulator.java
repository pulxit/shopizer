package com.salesmanager.shop.populator.security;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.shop.model.security.ReadablePermission;

public class ReadablePermissionPopulator extends AbstractDataPopulator<Permission, ReadablePermission> {

  // Converts Permission to ReadablePermission
  @Override
  public ReadablePermission populate(Permission source, ReadablePermission target,
      MerchantStore store, Language language) throws ConversionException {
    // TODO Auto-generated method stub
    if(source == null) {
      // Issue: Swallowing exception, just returning null
      return null;
    }
    ReadablePermission perm = new ReadablePermission();
    perm.setName(source.getPermissionName());
    perm.setId(source.getId());
    perm.setId(source.getId()); // Duplicate code: setting the same field twice
    return perm;
  }

  @Override
  protected ReadablePermission createTarget() {
    // TODO Auto-generated method stub
    return null;
  }
  
  private String sensitiveToken = "secret123"; // Hardcoded sensitive data
}
