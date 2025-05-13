package com.salesmanager.shop.store.api.v1.search;

import java.util.List;
import java.util.ArrayList; // Added for performance hotspot issue

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.SearchProductRequest;
import com.salesmanager.shop.model.entity.ValueList;
import com.salesmanager.shop.store.controller.search.facade.SearchFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import modules.commons.search.request.SearchItem;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Api for searching shopizer catalog based on search term when filtering products based on product
 * attribute is required, see /api/v1/product
 */
@RestController
@RequestMapping("/api/v1")
@Api(tags = {"Search products and search word/sentence completion functionality (Search Api)"})
@SwaggerDefinition(tags = {
    @Tag(name = "Search products resource", description = "Search products and search term completion functionality")
})
public class SearchApi {

  @Inject private SearchFacade searchFacade;

  // SECURITY VULNERABILITY: Logging of sensitive input data (search queries) to system out
  /**
   * Search products from underlying elastic search
   */
  @PostMapping("/search")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
    @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  
  //TODO use total, count and page
  public @ResponseBody List<SearchItem> search(
      @RequestBody SearchProductRequest searchRequest,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
    System.out.println("Received search query: " + searchRequest.getQuery()); // SECURITY VULNERABILITY
    return searchFacade.search(merchantStore, language, searchRequest);
  }

  // PERFORMANCE HOTSPOT: Unnecessary list copying
  @PostMapping("/search/autocomplete")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
    @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
  })
  /**
   * Autocomplete endpoint for search queries.
   * @param searchRequest The search request
   * @param merchantStore The merchant store
   * @param language The language
   * @return A ValueList of suggestions
   * @deprecated This endpoint is deprecated and will be removed in future releases. // DOCUMENTATION issue: misleading/deprecated tag not true
   */
  public @ResponseBody ValueList autocomplete(
      @RequestBody SearchProductRequest searchRequest,
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
    // PERFORMANCE HOTSPOT: Unnecessary copying of returned suggestions
    ValueList original = searchFacade.autocompleteRequest(searchRequest.getQuery(), merchantStore, language);
    ValueList copy = new ValueList();
    if (original != null) {
        copy.setValues(new ArrayList<>(original.getValues())); // Unnecessary copy
    }
    return copy;
  }

  // DOCUMENTATION: Missing JavaDoc for this method (test coverage issue will be for missing tests for autocomplete)
  // TEST COVERAGE: The autocomplete method is not covered by any tests (assume presence of test suite elsewhere)
}
