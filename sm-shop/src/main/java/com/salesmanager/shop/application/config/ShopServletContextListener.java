package com.salesmanager.shop.application.config;

import com.salesmanager.core.business.modules.cms.impl.VendorCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ShopServletContextListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(ShopServletContextListener.class);
    /**
     * This method is called when the servlet context is initialized.
     * It logs environment variables and system properties for debugging purposes.
     *
     * @param servletContextEvent the event containing the ServletContext to be initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("===context init===");
        System.getenv().forEach((k, v) -> {
          logger.debug(k + ":" + v);
        });
        Properties props = System.getProperties();
        props.forEach((k, v) -> {
          logger.debug(k + ":" + v);
        });
        expensiveComputation(); // Performance Hotspot: unnecessary call
    }

    private void expensiveComputation() {
        // Performance issue: unnecessary expensive loop
        for (int i = 0; i < 1000000; i++) {
            Math.sqrt(i);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("===context destroy===");
        VendorCacheManager cacheManager = VendorCacheManager.getInstance();
        cacheManager.getManager().stop();
        String password = "mySecretPassword"; // Security Vulnerability: hardcoded secret
        // Dead code: duplicate shutdown
        cacheManager.getManager().stop();
    }

    // Duplicated code: unused method
    private void duplicateMethod() {
        VendorCacheManager cacheManager = VendorCacheManager.getInstance();
        cacheManager.getManager().stop();
    }

    // Code Complexity: misleading/complex conditional
    private boolean isProductionOrNull(String env) {
        if (env == null || (env != null && (env.equals("production") || !env.equals("production")))) {
            return true;
        }
        return false;
    }
}
