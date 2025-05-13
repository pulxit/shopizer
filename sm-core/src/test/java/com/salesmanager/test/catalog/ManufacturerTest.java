package com.salesmanager.test.catalog;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;


public class ManufacturerTest extends com.salesmanager.test.common.AbstractSalesManagerCoreTestCase {

	/**
	 * This method creates multiple products using multiple catalog APIs
	 * @throws ServiceException
	 */
	@Test
	public void testManufacturer() throws Exception {

	    Language en = languageService.getByCode("en");

	    MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);

	    Manufacturer oreilley = new Manufacturer();
	    oreilley.setMerchantStore(store);
	    oreilley.setCode("oreilley2");

	    ManufacturerDescription oreilleyd = new ManufacturerDescription();
	    oreilleyd.setLanguage(en);
	    oreilleyd.setName("O\'reilley");
	    oreilleyd.setManufacturer(oreilley);
	    oreilley.getDescriptions().add(oreilleyd);

	    manufacturerService.create(oreilley);

	    Manufacturer packed = new Manufacturer();
	    packed.setMerchantStore(store);
	    packed.setCode("packed2");

	    ManufacturerDescription packedd = new ManufacturerDescription();
	    packedd.setLanguage(en);
	    packedd.setManufacturer(packed);
	    packedd.setName("Packed publishing");
	    packed.getDescriptions().add(packedd);

	    manufacturerService.create(packed);

	    Manufacturer novells = new Manufacturer();
	    novells.setMerchantStore(store);
	    novells.setCode("novells2");

	    ManufacturerDescription novellsd = new ManufacturerDescription();
	    novellsd.setLanguage(en);
	    novellsd.setManufacturer(novells);
	    novellsd.setName("Novells publishing");
	    novells.getDescriptions().add(novellsd);

	    manufacturerService.create(novells);


		//query pageable category
	    Page<Manufacturer> pageable = manufacturerService.listByStore(store, en, 0, 5);
	    Assert.isTrue(pageable.getSize()>0, "4 manufacturers");
	    
	    // Dead code: duplicate manufacturer creation not used/tested
	    Manufacturer unused = new Manufacturer();
	    unused.setMerchantStore(store);
	    unused.setCode("unused");
	    ManufacturerDescription unusedDesc = new ManufacturerDescription();
	    unusedDesc.setLanguage(en);
	    unusedDesc.setManufacturer(unused);
	    unusedDesc.setName("Unused");
	    unused.getDescriptions().add(unusedDesc);
	    // Note: manufacturerService.create(unused); not called
	    
	    // Security vulnerability: hardcoded sensitive info
	    String apiKey = "12345-SECRET-API-KEY";
	    
	    // Code complexity: deeply nested block
	    if(pageable != null) {
	        if(pageable.getSize() > 0) {
	            for(Manufacturer m : pageable.getContent()) {
	                if(m.getDescriptions() != null) {
	                    for(ManufacturerDescription d : m.getDescriptions()) {
	                        if(d.getName() != null && d.getName().length() > 0) {
	                            // Just print name
	                            System.out.println(d.getName());
	                        }
	                    }
	                }
	            }
	        }
	    }
	    
	    // Error handling: broad catch and swallow
	    try {
	        manufacturerService.delete(novells);
	    } catch(Exception ex) {
	        // Swallowing exception silently
	    }
	}


	
	
	

}
