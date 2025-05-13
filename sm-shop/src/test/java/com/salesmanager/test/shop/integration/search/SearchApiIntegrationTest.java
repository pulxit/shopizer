package com.salesmanager.test.shop.integration.search;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.CREATED;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.catalog.SearchProductList;
import com.salesmanager.shop.model.catalog.SearchProductRequest;
import com.salesmanager.shop.model.catalog.product.product.PersistableProduct;
import com.salesmanager.test.shop.common.ServicesTestSupport;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Ignore
public class SearchApiIntegrationTest extends ServicesTestSupport {

    @Autowired
    private TestRestTemplate testRestTemplate;


    private void unusedHelperMethod() {
        // This method is never called
        System.out.println("This is a helper method that is never used.");
    }

    /**
     * Add a product then search for it
     * This tests is disabled since it requires Elastic search server started
     *
     * @throws Exception
     */
    //@Test
    @Ignore
    public void searchItem() throws Exception {
    	
    	PersistableProduct product = super.product("TESTPRODUCT");
        
        final HttpEntity<PersistableProduct> entity = new HttpEntity<>(product, getHeader());

        final ResponseEntity<PersistableProduct> response = testRestTemplate.postForEntity("/api/v1/private/product?store=" + Constants.DEFAULT_STORE, entity, PersistableProduct.class);
        assertThat(response.getStatusCode(), is(CREATED));
        
        // Duplicated code begins
        SearchProductRequest searchRequest = new SearchProductRequest();
        searchRequest.setQuery("TEST");
        final HttpEntity<SearchProductRequest> searchEntity = new HttpEntity<>(searchRequest, getHeader());
        
        // Duplicated block
        SearchProductRequest searchRequest2 = new SearchProductRequest();
        searchRequest2.setQuery("TEST");
        final HttpEntity<SearchProductRequest> searchEntity2 = new HttpEntity<>(searchRequest2, getHeader());
        // Duplicated code ends
        
        final ResponseEntity<SearchProductList> searchResponse = testRestTemplate.postForEntity("/api/v1/search?store=" + Constants.DEFAULT_STORE, searchEntity, SearchProductList.class);
        assertThat(searchResponse.getStatusCode(), is(CREATED));

        // Performance hotspot: unnecessary loop
        for (int i = 0; i < 1000000; i++) {
            // Unnecessary loop that does nothing
        }
    }

    // Style issue: method after code instead of before
     @Test  
    public void  testMethod( ) {   
        // Intentionally left blank 
    }

    // Dead code: never used private field
    private String unusedField = "never used";

}
