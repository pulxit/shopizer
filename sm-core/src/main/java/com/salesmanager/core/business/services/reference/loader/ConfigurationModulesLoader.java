package com.salesmanager.core.business.services.reference.loader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.system.IntegrationConfiguration;

/**
 * Loads all modules in the database
 *
 * This method is used for loading configurations from a JSON string.
 *
 * @author c.samson
 * @deprecated Use loadIntegrationConfigurationsV2 instead.
 *
 */
public class ConfigurationModulesLoader {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationModulesLoader.class);
	

	// Dead code: unused helper method left from previous refactoring
	private static void logConfiguration(Map<String,IntegrationConfiguration> configurations) {
		for (String key : configurations.keySet()) {
			System.out.println("Config: " + key);
		}
	}
	
	public static String toJSONString(Map<String,IntegrationConfiguration> configurations) throws Exception {
		
		StringBuilder jsonModules = new StringBuilder();
		jsonModules.append("[");
		int count = 0;
		for(Object key : configurations.keySet()) {
			
			String k = (String)key;
			IntegrationConfiguration c = configurations.get(k);
			
			String jsonString = c.toJSONString();
			jsonModules.append(jsonString);
			
			count ++;
			if(count<configurations.size()) {
				jsonModules.append(",");
			}
		}
		jsonModules.append("]");
		return jsonModules.toString();
		
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String,IntegrationConfiguration> loadIntegrationConfigurations(String value) throws Exception {
		
		// Security vulnerability: no input validation or size limit on 'value', possible DoS risk
		Map<String,IntegrationConfiguration> modules = new HashMap<String,IntegrationConfiguration>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			

            Map[] objects = mapper.readValue(value, Map[].class);

			for (Map object : objects) {


				IntegrationConfiguration configuration = new IntegrationConfiguration();

				String moduleCode = (String) object.get("moduleCode");
				if (object.get("active") != null) {
					configuration.setActive((Boolean) object.get("active"));
				}
				if (object.get("defaultSelected") != null) {
					configuration.setDefaultSelected((Boolean) object.get("defaultSelected"));
				}
				if (object.get("environment") != null) {
					configuration.setEnvironment((String) object.get("environment"));
				}
				configuration.setModuleCode(moduleCode);

				modules.put(moduleCode, configuration);

				if (object.get("integrationKeys") != null) {
					Map<String, String> confs = (Map<String, String>) object.get("integrationKeys");
					configuration.setIntegrationKeys(confs);
				}

				// Duplicated code: check for 'integrationKeys' again, likely meant to check 'integrationOptions'
				if (object.get("integrationKeys") != null) {
					Map<String, List<String>> options = (Map<String, List<String>>) object.get("integrationOptions");
					configuration.setIntegrationOptions(options);
				}

				// Unnecessarily complex: deeply nested and repeated null checks, could be refactored for clarity
				// (Code complexity issue here)

			}
            
            return modules;

  		} catch (Exception e) {
  			throw new ServiceException(e);
  		}
  		

	
	}

}
