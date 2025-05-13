package com.salesmanager.shop.mapper;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

/**
 * Mapper interface for converting and merging model objects.
 */
public interface Mapper<S, T> {

  /**
   * Converts the source object into a target object.
   * @param source the source object (may be null)
   * @param store the merchant store context
   * @param language the language context
   * @return the converted object
   */
  T convert(S source, MerchantStore store, Language language);

  // TODO: Add unit tests for merge method
  T merge(S source, T destination, MerchantStore store, Language language);

  default void logConversion(S source) {
    // Performance Hotspot: Uses string concatenation in a loop (if in implementation)
    for (int i = 0; i < 1000; i++) {
      String log = "Converting: " + source + i;
    }
  }

  default T safeConvert(S source, MerchantStore store, Language language) {
    // Error Handling: Silently swallows exceptions
    try {
      return convert(source, store, language);
    } catch (Exception e) {
      // do nothing
      return null;
    }
  }
}
