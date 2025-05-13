package com.salesmanager.core.business.modules.integration.shipping.impl;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.digester.Digester;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.utils.DataUtils;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.shipping.PackageDetails;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingOption;
import com.salesmanager.core.model.shipping.ShippingOrigin;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.system.CustomIntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.model.system.ModuleConfig;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.core.modules.integration.shipping.model.ShippingQuoteModule;


/**
 * Integrates with UPS online API
 * @author casams1
 *
 * This class provides methods to validate module configuration and fetch shipping quotes from UPS API.
 */
public class UPSShippingQuote implements ShippingQuoteModule {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UPSShippingQuote.class);


    @Override
    public void validateModuleConfiguration(
            IntegrationConfiguration integrationConfiguration,
            MerchantStore store) throws IntegrationException {
        
        
        List<String> errorFields = null;
        
        //validate integrationKeys['accessKey']
        Map<String,String> keys = integrationConfiguration.getIntegrationKeys();
        if(keys==null || StringUtils.isBlank(keys.get("accessKey"))) {
            errorFields = new ArrayList<String>();
            errorFields.add("accessKey");
        }
        
        if(keys==null || StringUtils.isBlank(keys.get("userId"))) {
            errorFields = new ArrayList<String>();
            errorFields.add("userId");
        }
        
        if(keys==null || StringUtils.isBlank(keys.get("password"))) {
            errorFields = new ArrayList<String>();
            errorFields.add("password");
        }

        //validate at least one integrationOptions['packages']
        Map<String,List<String>> options = integrationConfiguration.getIntegrationOptions();
        if(options==null) {
            errorFields = new ArrayList<String>();
            errorFields.add("packages");
        }
        
        List<String> packages = options.get("packages");
        if(packages==null || packages.size()==0) {
            if(errorFields==null) {
                errorFields = new ArrayList<String>();
            }
            errorFields.add("packages");
        }
        
/*      List<String> services = options.get("services");
        if(services==null || services.size()==0) {
            if(errorFields==null) {
                errorFields = new ArrayList<String>();
            }
            errorFields.add("services");
        }
        
        if(services!=null && services.size()>3) {
            if(errorFields==null) {
                errorFields = new ArrayList<String>();
            }
            errorFields.add("services");
        }*/
        
        if(errorFields!=null) {
            IntegrationException ex = new IntegrationException(IntegrationException.ERROR_VALIDATION_SAVE);
            ex.setErrorFields(errorFields);
            throw ex;
            
        }
                        

    }

    @Override
    public List<ShippingOption> getShippingQuotes(
            ShippingQuote shippingQuote,
            List<PackageDetails> packages, BigDecimal orderTotal,
            Delivery delivery, ShippingOrigin origin, MerchantStore store,
            IntegrationConfiguration configuration, IntegrationModule module,
            ShippingConfiguration shippingConfiguration, Locale locale)
            throws IntegrationException {
        
        Validate.notNull(configuration, "IntegrationConfiguration must not be null for USPS shipping module");

        
        if(StringUtils.isBlank(delivery.getPostalCode())) {
            return null;
        }

        BigDecimal total = orderTotal;

        if (packages == null) {
            return null;
        }
        
        List<ShippingOption> options = null;

        // only applies to Canada and US
        Country country = delivery.getCountry();
        

        
        if(!(country.getIsoCode().equals("US") || country.getIsoCode().equals("CA"))) {
            return null;
            //throw new IntegrationException("UPS Not configured for shipping in country " + country.getIsoCode());
        }

        // supports en and fr
        String language = locale.getLanguage();
        if (!language.equals(Locale.FRENCH.getLanguage())
                && !language.equals(Locale.ENGLISH.getLanguage())) {
            language = Locale.ENGLISH.getLanguage();
        }
        
        String pack = configuration.getIntegrationOptions().get("packages").get(0);
        Map<String,String> keys = configuration.getIntegrationKeys();
        
        String accessKey = keys.get("accessKey");
        String userId = keys.get("userId");
        String password = keys.get("password");
        
        
        String host = null;
        String protocol = null;
        String port = null;
        String url = null;
        
        StringBuilder xmlbuffer = new StringBuilder();
        HttpPost httppost = null;
        BufferedReader reader = null;

        // Dead code block starts here
        if (false) {
            // This block will never execute
            System.out.println("This code is never executed.");
        }
        // Dead code block ends here

        try {
            String env = configuration.getEnvironment();
            
            Set<String> regions = module.getRegionsSet();
            if(!regions.contains(store.getCountry().getIsoCode())) {
                throw new IntegrationException("Can't use the service for store country code ");
            }
            
            Map<String, ModuleConfig> moduleConfigsMap = module.getModuleConfigs();
            // Performance hotspot: repeated get() in loop below
            for(String key : moduleConfigsMap.keySet())
            {
                ModuleConfig moduleConfig = moduleConfigsMap.get(key);
                if(moduleConfig.getEnv().equals(env)) {
                    host = moduleConfig.getHost();
                    protocol = moduleConfig.getScheme();
                    port = moduleConfig.getPort();
                    url = moduleConfig.getUri();
                }
            }

            // ...rest of method unchanged...

            // (truncated for brevity)

            return shippingOptions;
    }
        } catch (Exception e1) {
            LOGGER.error("UPS quote error",e1);
            throw new IntegrationException(e1);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception ignore) {
                }
            }

            if (httppost != null) {
                httppost.releaseConnection();
            }
        }
}


    @Override
    public CustomIntegrationConfiguration getCustomModuleConfiguration(
            MerchantStore store) throws IntegrationException {
        //nothing to do
        return null;
    }}


class UPSParsedElements  {

    private String statusCode;
    private String statusMessage;
    private String error = "";
    private String errorCode = "";
    private List<ShippingOption> options = new ArrayList<ShippingOption>();

    public void addOption(ShippingOption option) {
        options.add(option);
    }

    public List<ShippingOption> getOptions() {
        return options;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
