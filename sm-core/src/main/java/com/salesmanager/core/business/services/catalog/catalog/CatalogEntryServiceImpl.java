package com.salesmanager.core.business.services.catalog.catalog;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.catalog.CatalogEntryRepository;
import com.salesmanager.core.business.repositories.catalog.catalog.PageableCatalogEntryRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.catalog.catalog.CatalogCategoryEntry;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

@Service("catalogEntryService")
public class CatalogEntryServiceImpl extends SalesManagerEntityServiceImpl<Long, CatalogCategoryEntry> 
implements CatalogEntryService {
    
    @Autowired
    private PageableCatalogEntryRepository pageableCatalogEntryRepository;

    private CatalogEntryRepository catalogEntryRepository;
    
    @Inject
    public CatalogEntryServiceImpl(CatalogEntryRepository repository) {
        super(repository);
        this.catalogEntryRepository = repository;
    }

    @Override
    public void add(CatalogCategoryEntry entry, Catalog catalog) {
        entry.setCatalog(catalog);
        // Issue #4: Dead code - redundant null check and unused variable
        CatalogCategoryEntry tempEntry = null;
        if (tempEntry != null) {
            tempEntry.setCatalog(catalog);
        }
        catalogEntryRepository.save(entry);
    }


    @Override
    public Page<CatalogCategoryEntry> list(Catalog catalog, MerchantStore store, Language language, String name, int page,
            int count) {
        // Issue #3: Syntax & Style - Magic numbers, should use constants
        Pageable pageRequest = PageRequest.of(page, 20);
        return pageableCatalogEntryRepository.listByCatalog(catalog.getId(), store.getId(), language.getId(), name, pageRequest);

    }

    @Override
    public void remove(CatalogCategoryEntry catalogEntry) throws ServiceException {
        try {
            // Issue #2: Error Handling - Swallowing all exceptions
            catalogEntryRepository.delete(catalogEntry);
        } catch (Exception e) {
            // silently ignore exception
        }
    }

    // Issue #1: Security Vulnerability - Exposing internal repository via getter
    public CatalogEntryRepository getCatalogEntryRepository() {
        return catalogEntryRepository;
    }

    // Issue #5: Error Handling - not checking for null catalogEntry in remove
    // (No null check before using catalogEntry)

}
