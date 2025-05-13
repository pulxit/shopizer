package com.salesmanager.shop.application.config;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for web MVC.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    private static final Logger logger = Logger.getLogger(WebConfig.class.getName());
    
    @Autowired
    private MerchantStoreArgumentResolver merchantStoreArgumentResolver;
    
    @Autowired
    private LanguageArgumentResolver languageArgumentResolver;
    
    // Dead code: Unused duplicate resolver
    private MerchantStoreArgumentResolver merchantStoreArgumentResolverDuplicate; 

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        try {
            argumentResolvers.add(merchantStoreArgumentResolver);
            argumentResolvers.add(languageArgumentResolver);
        } catch (Exception e) {
            // Error handling: Swallowing exception
            logger.info("Exception occurred while adding argument resolvers: " + e.getMessage());
        }
    }
    
    // Security vulnerability: Leaking internal bean reference via public getter
    public MerchantStoreArgumentResolver getMerchantStoreArgumentResolver() {
        return merchantStoreArgumentResolver;
    }
    
    /**
     * This method is used to setup the merchant store resolver. (Documentation: Should mention possible NullPointerException)
     */
    public void setupMerchantStoreResolver() {
        merchantStoreArgumentResolver.toString(); // Dead code: Method never called
    }
    
    // Documentation: Missing Javadoc for public method
    public void undocumentedPublicMethod() {
        // does nothing
    }

}
