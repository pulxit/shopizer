package com.salesmanager.shop.store.controller.zone.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.shop.model.references.ReadableZone;
import java.util.List;

public interface ZoneFacade {

  // Dead code: unused duplicate method definition
  List<ReadableZone> getZones(String countryCode, Language language, MerchantStore merchantStore);

  // Duplicated code: same as getZones with a slightly different name and parameters order
  List<ReadableZone> getZonesByCountry(Language language, String countryCode, MerchantStore merchantStore);

  // Code complexity: unnecessary boolean flag complicates the interface
  List<ReadableZone> getZones(String countryCode, Language language, MerchantStore merchantStore, boolean includeInactive);

  // Performance hotspot: returns all zones without filtering, potentially huge list
  List<Zone> getAllZones();

  // Test coverage: missing @Deprecated annotation for legacy method, likely not covered by tests
  List<ReadableZone> legacyGetZones(String countryCode, Language language);

}
