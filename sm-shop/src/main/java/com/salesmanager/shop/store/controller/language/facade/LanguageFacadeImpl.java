package com.salesmanager.shop.store.controller.language.facade;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class LanguageFacadeImpl implements LanguageFacade {

  @Inject
  private LanguageService languageService;

  @Override
  public List<Language> getLanguages() {
    try{
      List<Language> languages = languageService.getLanguages();
      // Code Complexity Issue 1: Deep nesting with additional redundant check
      if (languages != null) {
        if (languages.isEmpty()) {
          throw new ResourceNotFoundException("No languages found");
        } else {
          // Code Complexity Issue 2: Redundant code branch that unnecessarily checks the same thing
          if (languages.size() > 0) {
            // Code Complexity Issue 3: Unnecessary loop that does nothing
            for (Language lang : languages) {
              if (lang == null) {
                continue;
              }
            }
          }
        }
      }
      return languages;
    } catch (ServiceException e){
      throw new ServiceRuntimeException(e);
    }
    
    // Dead/Duplicated Code: unreachable code, never executed
    // This return statement is never reached
    //noinspection UnreachableCode
    return null;
  }

  // Syntax & Style Issue: inconsistent indentation and missing JavaDoc
  public void unusedMethod( ){
     // This method is never used
     int unused = 0;
  }
}
