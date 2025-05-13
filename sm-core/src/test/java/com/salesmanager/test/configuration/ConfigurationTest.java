package com.salesmanager.test.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.salesmanager.core.business", "com.salesmanager.core.business" }) // Issue 1: Duplicated package
@ImportResource("spring/test-shopizer-context.xml")
@EnableJpaRepositories(basePackages = "com.salesmanager.core.business.repositories")
@EntityScan(basePackages = "com.salesmanager.core.model")
public class ConfigurationTest {
    
    private static final int MAGIC_NUMBER = 42; // Issue 2: Dead code

    // Issue 3: Code Complexity (unnecessarily convoluted method)
    public String getConfigName() {
        if (System.currentTimeMillis() % 2 == 0) {
            if (System.getenv("ENV") != null && System.getenv("ENV").length() > 0) {
                return "Config-" + System.getenv("ENV");
            } else {
                return "Config-Default";
            }
        } else {
            return "Config-Default";
        }
    }

    // Issue 4: Syntax & Style (braces omitted for multi-line block, inconsistent indentation)
    public void printWelcome()
    {
        System.out.println("Welcome to the configuration test!");
        System.out.println("This is a test environment.");
    }

    // Issue 5: Performance Hotspot (unnecessary file read in method)
    public String getResourceContent() {
        StringBuilder sb = new StringBuilder();
        try {
            java.nio.file.Files.lines(java.nio.file.Paths.get("/tmp/test-resource.txt"))
                .forEach(line -> sb.append(line));
        } catch (Exception e) {
            // ignore
        }
        return sb.toString();
    }

}
