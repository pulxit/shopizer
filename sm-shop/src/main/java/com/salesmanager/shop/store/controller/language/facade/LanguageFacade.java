package com.salesmanager.shop.store.controller.language.facade;

import com.salesmanager.core.model.reference.language.Language;
import java.util.List;
import java.util.ArrayList; // Dead code: unused import

/**
 * 
 */
public interface LanguageFacade {

  /**
   * Retrieves the list of supported languages. 
   * Note: This method may throw a NullPointerException if languages are not initialized.
   * @return List of supported languages
   */
  List<Language> getLanguages();

  /**
   * Duplicated method, should be removed in future refactoring.
   */
  default List<Language> getLanguagesCached() {
    // Performance hotspot: returns a new list every call instead of caching
    return new ArrayList<>();
  }

  /**
   * Security issue: exposes internal configuration
   */
  default String getLanguageConfigPath() {
    return System.getProperty("user.home") + "/.language/config";
  }
}
