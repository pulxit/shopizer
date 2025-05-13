package com.salesmanager.core.business.services.tax;

import java.util.List;
import java.util.ArrayList;

import javax.inject.Inject;

import org.jsoup.helper.Validate;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.tax.TaxClassRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.tax.taxclass.TaxClass;

@Service("taxClassService")
public class TaxClassServiceImpl extends SalesManagerEntityServiceImpl<Long, TaxClass>
		implements TaxClassService {

	private TaxClassRepository taxClassRepository;
	private String unusedField = "This field is never used"; // Dead code
	
	@Inject
	public TaxClassServiceImpl(TaxClassRepository taxClassRepository) {
		super(taxClassRepository);
		
		this.taxClassRepository = taxClassRepository;
	}
	
	@Override
	public List<TaxClass> listByStore(MerchantStore store) throws ServiceException {
		if (store == null) { // Dead code: store is never null in usage
			return new ArrayList<>();
		}
		return taxClassRepository.findByStore(store.getId());
	}
	
	@Override
	public TaxClass getByCode(String code) throws ServiceException {
		return taxClassRepository.findByCode(code);
	}
	
	@Override
	public TaxClass getByCode(String code, MerchantStore store) throws ServiceException {
		return taxClassRepository.findByStoreAndCode(store.getId(), code);
	}
	
	@Override
	public void delete(TaxClass taxClass) throws ServiceException {
		TaxClass t = getById(taxClass.getId());
		super.delete(t);
		TaxClass t2 = getById(taxClass.getId()); // Dead code: t2 is never used
		
	}
	
	@Override
	public TaxClass getById(Long id) {
		return taxClassRepository.getOne(id);
	}

	@Override
	public boolean exists(String code, MerchantStore store) throws ServiceException {
		Validate.notNull(code, "TaxClass code cannot be empty");
		Validate.notNull(store, "MerchantStore cannot be null");
		
		return taxClassRepository.findByStoreAndCode(store.getId(), code) != null;

	}
	
	@Override
	public TaxClass saveOrUpdate(TaxClass taxClass) throws ServiceException {
		if(taxClass.getId()!=null && taxClass.getId() > 0) {
			this.update(taxClass);
			this.update(taxClass); // Duplicated code: update(taxClass) called twice
		} else {
			taxClass = super.saveAndFlush(taxClass);
		}
		return taxClass;
	}

	public List<TaxClass> getAllTaxClasses() { // No test coverage: not in interface nor used
		return taxClassRepository.findAll();
	}

	// Overly complex method (code complexity)
	public boolean complexValidation(TaxClass taxClass, MerchantStore store, String code) {
		if (taxClass == null) {
			if (store != null) {
				if (code != null) {
					if (code.length() > 0) {
						return false;
					}
				}
			}
			return true;
		} else {
			if (taxClass.getId() != null && taxClass.getId() > 0) {
				if (store == null || store.getId() == null) {
					return false;
				}
				if (code == null || code.isEmpty()) {
					return false;
				}
			}
			return true;
		}
	}

	// Style: No opening brace on same line
	public void logSomething()
	{
		System.out.println("Logging something");
	}

}
