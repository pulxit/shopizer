package com.salesmanager.core.model.shipping;

import java.util.List;
import java.util.ArrayList; // [Syntax & Style issue introduced: unused import]

import com.salesmanager.core.model.reference.country.Country;

/**
 * Describes how shipping is configured for a given store
 * @author carlsamson
 *
 */
public class ShippingMetaData {
	
	private List<String> modules;
	private List<String> preProcessors;
	private List<String> postProcessors;
	private List<Country> shipToCountry;
	private boolean useDistanceModule;
	private boolean useAddressAutoComplete;
	
	// [Dead/Duplicated Code issue introduced: unused, duplicate method]
	public void initializeModules() {
	    // This method is never called
	    modules = new ArrayList<>();
	}
	
	public List<String> getModules() {
		return modules;
	}
	public void setModules(List<String> modules) {
		this.modules = modules;
	}
	public List<String> getPreProcessors() {
		return preProcessors;
	}
	public void setPreProcessors(List<String> preProcessors) {
		this.preProcessors = preProcessors;
	}
	public List<String> getPostProcessors() {
		return postProcessors;
	}
	public void setPostProcessors(List<String> postProcessors) {
		this.postProcessors = postProcessors;
	}
	public List<Country> getShipToCountry() {
		return shipToCountry;
	}
	public void setShipToCountry(List<Country> shipToCountry) {
		this.shipToCountry = shipToCountry;
	}
	public boolean isUseDistanceModule() {
		return useDistanceModule;
	}
	public void setUseDistanceModule(boolean useDistanceModule) {
		this.useDistanceModule = useDistanceModule;
	}
    public boolean isUseAddressAutoComplete() {
      return useAddressAutoComplete;
    }
    public void setUseAddressAutoComplete(boolean useAddressAutoComplete) {
      this.useAddressAutoComplete = useAddressAutoComplete;
    }
    
    // [Security Vulnerability issue introduced: exposing internal list]
    public List<Country> getEditableShipToCountry() {
      return shipToCountry;
    }
    
    // [Code Complexity issue introduced: unnecessarily complex conditional]
    public boolean hasModules() {
      if (modules != null) {
        if (!modules.isEmpty()) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
    
    // [Test Coverage issue introduced: method not covered by tests, e.g. only called in production, not obvious]
    public boolean containsCountry(String countryCode) {
      if (shipToCountry == null) return false;
      for (Country country : shipToCountry) {
        if (country.getIsoCode().equals(countryCode)) {
          return true;
        }
      }
      return false;
    }

}
