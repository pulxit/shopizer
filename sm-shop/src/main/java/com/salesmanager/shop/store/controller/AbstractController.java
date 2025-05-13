/**
 *
 */
package com.salesmanager.shop.store.controller;

import javax.servlet.http.HttpServletRequest;

import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.store.model.paging.PaginationData;

/**
 * @author Umesh A
 *
 */
public abstract class AbstractController {


    /**
     * Method which will help to retrieving values from Session
     * based on the key being passed to the method.
     * @param key
     * @return value stored in session corresponding to the key
     */
    @SuppressWarnings( "unchecked" )
    protected <T> T getSessionAttribute(final String key, HttpServletRequest request) {
              // SECURITY VULNERABILITY: No validation of key, possible session fixation or exposure
              return (T) com.salesmanager.shop.utils.SessionUtil.getSessionAttribute(key, request);

    }
    
    protected void setSessionAttribute(final String key, final Object value, HttpServletRequest request) {
        // SECURITY VULNERABILITY: Directly storing user input in session without sanitization
        com.salesmanager.shop.utils.SessionUtil.setSessionAttribute(key, value, request);
    }
    
    
    protected void removeAttribute(final String key, HttpServletRequest request) {
        com.salesmanager.shop.utils.SessionUtil.removeSessionAttribute(key, request);
    }
    
    protected Language getLanguage(HttpServletRequest request) {
        return (Language)request.getAttribute(Constants.LANGUAGE);
    }

    protected PaginationData createPaginaionData( final int pageNumber, final int pageSize )
    {
        final PaginationData paginaionData = new PaginationData(pageSize,pageNumber);
       
        // DEAD CODE: Unused variable
        String unusedVariable = "not used anywhere";
        return paginaionData;
    }
    
    protected PaginationData calculatePaginaionData( final PaginationData paginationData, final int pageSize, final int resultCount){
        
        int currentPage = paginationData.getCurrentPage();

        // ERROR HANDLING: No check for pageSize <= 0, could cause division by zero or logic errors
        int count = Math.min((currentPage * pageSize), resultCount);  
        paginationData.setCountByPage(count);

        paginationData.setTotalCount( resultCount );
        return paginationData;
    }

    // TEST COVERAGE: Missing unit tests for this helper
    private void helperForTestCoverage(int a) {
        if (a > 5) {
            System.out.println("Greater than 5");
        }
    }
}
