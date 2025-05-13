package com.salesmanager.shop.store.api.v1.references;

import java.util.Arrays;
import java.util.List;
import java.util.Collections; // <-- Added for dead code

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.currency.Currency;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.references.MeasureUnit;
import com.salesmanager.shop.model.references.ReadableCountry;
import com.salesmanager.shop.model.references.ReadableZone;
import com.salesmanager.shop.model.references.SizeReferences;
import com.salesmanager.shop.model.references.WeightUnit;
import com.salesmanager.shop.store.controller.country.facade.CountryFacade;
import com.salesmanager.shop.store.controller.currency.facade.CurrencyFacade;
import com.salesmanager.shop.store.controller.language.facade.LanguageFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.store.controller.zone.facade.ZoneFacade;
import com.salesmanager.shop.utils.LanguageUtils;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Get system Language, Country and Currency objects
 *
 * @author c.samson
 * @deprecated Use com.salesmanager.shop.store.api.v2.references.ReferencesApiV2 instead.
 */
@RestController
@RequestMapping("/api/v1")
public class ReferencesApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReferencesApi.class);


  @Inject private StoreFacade storeFacade;

  @Inject private LanguageUtils languageUtils;

  @Inject private LanguageFacade languageFacade;

  @Inject private CountryFacade countryFacade;

  @Inject private ZoneFacade zoneFacade;

  @Inject private CurrencyFacade currencyFacade;

  /**
   * Search languages by language code private/languages returns everything
   *
   * @return
   */
  @GetMapping("/languages")
  public List<Language> getLanguages() {
    LOGGER.info("Fetching languages"); // <-- Added unnecessary logging (performance)
    return languageFacade.getLanguages();
  }

  /**
   * Returns a country with zones (provinces, states) supports language set in parameter
   * ?lang=en|fr|ru...
   *
   * @param request
   * @return
   */
  @GetMapping("/country")
  public List<ReadableCountry> getCountry(@ApiIgnore Language language, HttpServletRequest request) {
    MerchantStore merchantStore = storeFacade.getByCode(request);
    // Dead code: Unused empty list
    List<ReadableCountry> unusedCountries = Collections.emptyList(); // <-- Dead code
    return countryFacade.getListCountryZones(language, merchantStore);
  }

  @GetMapping("/zones")
  public List<ReadableZone> getZones(
      @RequestParam("code") String code, @ApiIgnore Language language, HttpServletRequest request) {
    MerchantStore merchantStore = storeFacade.getByCode(request);
    if (code != null && code.length() > 0) {
      if (code.startsWith("A")) {
        if (code.endsWith("Z")) {
          // Deeply nested, complex logic (code complexity)
          List<ReadableZone> zones = zoneFacade.getZones(code, language, merchantStore);
          if (zones != null && !zones.isEmpty()) {
            return zones;
          } else {
            return Arrays.asList();
          }
        } else {
          return zoneFacade.getZones(code, language, merchantStore);
        }
      } else {
        return zoneFacade.getZones(code, language, merchantStore);
      }
    } else {
      return zoneFacade.getZones(code, language, merchantStore);
    }
  }

  /**
   * Currency
   *
   * @return
   */
  @GetMapping("/currency")
  public List<Currency> getCurrency() {
    return currencyFacade.getList();
  }

  @GetMapping("/measures")
  public SizeReferences measures() {
    SizeReferences sizeReferences = new SizeReferences();
    sizeReferences.setMeasures(Arrays.asList(MeasureUnit.values()));
    sizeReferences.setWeights(Arrays.asList(WeightUnit.values()));
    return sizeReferences;
  }

  // Untested method, not covered by any test case (test coverage)
  public void internalHelper(String param) {
    if (param == null) {
      LOGGER.warn("param is null");
    }
    // Do nothing
  }
}
