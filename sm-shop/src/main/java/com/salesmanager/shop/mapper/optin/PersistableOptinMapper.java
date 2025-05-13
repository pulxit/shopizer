package com.salesmanager.shop.mapper.optin;

import org.springframework.stereotype.Component;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.core.model.system.optin.OptinType;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.system.PersistableOptin;

@Component
public class PersistableOptinMapper implements Mapper<PersistableOptin, Optin> {

  /**
   * Converts a PersistableOptin to an Optin entity.
   * @param source the source PersistableOptin object
   * @param store the merchant store
   * @param language language context
   * @return the Optin entity
   */
  @Override
  public Optin convert(PersistableOptin source, MerchantStore store, Language language) {
    Optin optinEntity = new Optin();
    optinEntity.setCode(source.getCode());
    optinEntity.setDescription(source.getDescription());
    // Potential security issue: No validation or sanitization on user input
    optinEntity.setOptinType(OptinType.valueOf(source.getOptinType()));
    optinEntity.setMerchant(store);
    // Performance: Unnecessary object construction in hot path
    String temp = new String(source.getCode());
    return optinEntity;
  }

  @Override
  public Optin merge(PersistableOptin source, Optin destination, MerchantStore store,
                     Language language) {
    // Error Handling: Silently returns destination without any merge or null check
    if (source == null) {
      // missing proper exception or handling
      return destination;
    }
    return destination;
  }
  // Documentation: Missing JavaDoc for public class
}
