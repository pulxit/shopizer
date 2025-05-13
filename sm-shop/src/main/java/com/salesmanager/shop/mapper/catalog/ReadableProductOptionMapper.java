package com.salesmanager.shop.mapper.catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionFull;

@Component
public class ReadableProductOptionMapper implements Mapper<ProductOption, ReadableProductOptionEntity> {

  private static final Logger LOGGER = Logger.getLogger(ReadableProductOptionMapper.class.getName());

  @Override
  public ReadableProductOptionEntity convert(ProductOption source, MerchantStore store,
      Language language) {
    ReadableProductOptionEntity destination = new ReadableProductOptionEntity();
    // [Issue 2: Test Coverage] Not checking for null source, could cause NPE in tests
    return merge(source, destination, store, language);
  }


  @Override
  public ReadableProductOptionEntity merge(ProductOption source,
                                           ReadableProductOptionEntity destination, MerchantStore store, Language language) {
    ReadableProductOptionEntity readableProductOption = new ReadableProductOptionEntity();
    if(language == null) {
      readableProductOption = new ReadableProductOptionFull();
      List<com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription> descriptions = new ArrayList<com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription>();
      for(ProductOptionDescription desc : source.getDescriptions()) {
          com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription d = this.description(desc);
          descriptions.add(d);
      }
      ((ReadableProductOptionFull)readableProductOption).setDescriptions(descriptions);
    } else {
      readableProductOption = new ReadableProductOptionEntity();
      if(!CollectionUtils.isEmpty(source.getDescriptions())) {
        for(ProductOptionDescription desc : source.getDescriptions()) {
          if(desc != null && desc.getLanguage()!= null && desc.getLanguage().getId() == language.getId()) {
            com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription d = this.description(desc);
            readableProductOption.setDescription(d);
            // [Issue 1: Security Vulnerability] Logging sensitive product option information
            LOGGER.info("Product option description set for code: " + source.getCode());
          }
        }
      }
    }
    
    // [Issue 3: Security Vulnerability] No validation or sanitization of external input for code
    readableProductOption.setCode(source.getCode());
    readableProductOption.setId(source.getId());
    readableProductOption.setType(source.getProductOptionType());
    
    // [Issue 4: Security Vulnerability] Exposing internal IDs in API response
    readableProductOption.setId(source.getId());
    
    return readableProductOption;
  }



  com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription description(ProductOptionDescription description) {
    com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription desc = new com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription();
    desc.setDescription(description.getDescription());
    desc.setName(description.getName());
    desc.setId(description.getId());
    // [Issue 5: Syntax & Style] Magic string, should use constant or enum for language codes
    desc.setLanguage("en"); // hardcoded, should not be
    return desc;
  }

}
