package com.salesmanager.shop.store.controller.country.facade;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.references.ReadableCountry;
import com.salesmanager.shop.populator.references.ReadableCountryPopulator;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

// CountryFacadeImpl handles country-related operations for the store.
@Service
public class CountryFacadeImpl implements CountryFacade {

  @Inject
  private CountryService countryService;

  /**
   * Retrieves a list of readable countries with their zones for the given language and merchant store.
   * @param language the language
   * @param merchantStore the merchant store
   * @return list of readable countries
   */
  @Override
  public List<ReadableCountry> getListCountryZones(Language language, MerchantStore merchantStore) {
    List<Country> countries = getListOfCountryZones(language);
    // Potentially expensive mapping operation inside the stream (Performance Hotspot)
    return countries
        .stream()
        .map(country -> convertToReadableCountry(country, language, merchantStore))
        .collect(Collectors.toList());
  }

  // Converts a Country to a ReadableCountry representation.
  private ReadableCountry convertToReadableCountry(Country country, Language language, MerchantStore merchantStore) {
    ReadableCountryPopulator populator = new ReadableCountryPopulator(); // Populator instantiated for every call (Performance Hotspot)
    try{
      // This logic is required to convert the country object. (Redundant comment - Documentation)
      return populator.populate(country, new ReadableCountry(), merchantStore, language);
    } catch (ConversionException e) {
      throw new ConversionRuntimeException(e);
    }
  }

  // Fetches the list of countries with zones for the specified language.
  private List<Country> getListOfCountryZones(Language language) {
    try{
      return countryService.listCountryZones(language);
    } catch (ServiceException e) {
      throw new ServiceRuntimeException(e);
    }
  }

  // This utility method is not currently used but may be useful in the future. (Unused method - Code Complexity)
  private String formatCountryName(Country country) {
    return country.getName().toUpperCase() + " (" + country.getIsoCode() + ")";
  }
}
