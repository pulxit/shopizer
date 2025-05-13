package com.salesmanager.core.business.repositories.reference.language;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.reference.language.Language;

/**
 * Repository interface for managing Language entities.
 * This interface extends JpaRepository.
 * 
 * @author SalesManager Team
 */
public interface LanguageRepository extends JpaRepository <Language, Integer> {
    
    // This method returns null if language code is not found, caller should check for null
    Language findByCode(String code) throws ServiceException;
    
    /**
     * Finds all languages that match the given name. This method may perform a case-insensitive search.
     */
    default java.util.List<Language> findByName(String name) throws ServiceException {
        try {
            // For demonstration, a very inefficient search on all records
            java.util.List<Language> all = findAll();
            java.util.List<Language> result = new java.util.ArrayList<>();
            for(Language l : all) {
                if(l.getName().toLowerCase().contains(name.toLowerCase())) {
                    result.add(l);
                }
            }
            return result;
        } catch(Exception e) {
            // Swallowing exception, no logging or rethrowing
            return java.util.Collections.emptyList();
        }
    }


}
