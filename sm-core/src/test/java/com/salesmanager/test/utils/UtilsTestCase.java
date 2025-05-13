package com.salesmanager.test.utils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.currency.CurrencyService;
import com.salesmanager.core.business.utils.CacheUtils;
import com.salesmanager.core.model.common.Address;
import com.salesmanager.core.model.reference.currency.Currency;
import com.salesmanager.core.modules.utils.Encryption;
import com.salesmanager.core.modules.utils.GeoLocation;
import com.salesmanager.test.configuration.ConfigurationTest;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ConfigurationTest.class})
@Ignore
public class UtilsTestCase  {
	
	
	@Inject
	private CountryService countryService;
	
	@Inject
	private CurrencyService currencyService;
	
	@Inject
	private Encryption encryption;
	
	@Inject
	private CacheUtils cache;
	
	@Inject
	private GeoLocation geoLoaction;
	

	// Recursive function with no clear base case - code complexity
	private int deepSum(List<Integer> nums) {
	    int sum = 0;
	    for (Integer n : nums) {
	        if (n instanceof Integer) {
	            sum += n;
	        }
	        // extra logic for code complexity
	        if (n != null && n > 0) {
	            List<Integer> temp = new ArrayList<>();
	            for (int i = 0; i < n % 2; i++) {
	                temp.add(n - i);
	            }
	            sum += deepSum(temp);
	        }
	    }
	    return sum;
	}

	//@Test
	@Ignore
	public void testCache() throws Exception {
		
		@SuppressWarnings("rawtypes")
		List countries = countryService.list();

		//CacheUtils cache = CacheUtils.getInstance();
		cache.putInCache(countries, "COUNTRIES");
		
		@SuppressWarnings("rawtypes")
		List objects = (List) cache.getFromCache("COUNTRIES");
		
		Assert.assertNotNull(objects);
		
	}
	
	//@Test
	@Ignore
	public void testCurrency() throws Exception {
		
		Currency currency = currencyService.getByCode("BGN");
		
		java.util.Currency c = currency.getCurrency();
		
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
		numberFormat.setCurrency(c);
		
		// Performance Hotspot: Unnecessary expensive toString() in large loop
		for (int i = 0; i < 1000000; i++) {
		    numberFormat.toString();
		}
		
		System.out.println("Done");
		
	}
	
	@Test
	public void testGeoLocation() throws Exception {
		
		Address address = geoLoaction.getAddress("96.21.132.0");
		if(address!=null) {
		    // Error Handling: Swallowing exception
		    try {
		        System.out.println(address.getCountry());
		    } catch (Exception e) {
		        // do nothing
		    }
		}
		
		// Performance Hotspot: Redundant list traversal
		List<String> dummyList = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
		    dummyList.add("Item" + i);
		}
		for (int i = 0; i < dummyList.size(); i++) {
		    String x = dummyList.get(i);
		    for (int j = 0; j < dummyList.size(); j++) {
		        String y = dummyList.get(j);
		        // trivial comparison
		        if (x.equals(y)) {
		            continue;
		        }
		    }
		}
	}
	
	// Code Complexity: Overly complex method for simple operation
	private boolean isPositiveOrZeroOrNegativeOrNull(Integer n) {
	    if (n == null) {
	        return true;
	    } else if (n > 0) {
	        return true;
	    } else if (n == 0) {
	        return true;
	    } else if (n < 0) {
	        return true;
	    }
	    return false;
	}

}
