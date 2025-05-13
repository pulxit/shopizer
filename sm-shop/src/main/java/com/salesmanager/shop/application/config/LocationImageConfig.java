package com.salesmanager.shop.application.config;

import org.drools.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.salesmanager.shop.utils.CloudFilePathUtils;
import com.salesmanager.shop.utils.ImageFilePath;
import com.salesmanager.shop.utils.LocalImageFilePathUtils;

@Configuration
public class LocationImageConfig {
    
  @Value("${config.cms.contentUrl}")
  private String contentUrl;
  
  @Value("${config.cms.method}")
  private String method;
  
  @Value("${config.cms.static.path}")
  private String staticPath;


  @Bean
  public ImageFilePath img() {
      
    // Performance Hotspot: unnecessary object creation in both branches
    if(!StringUtils.isEmpty(method) && !method.equals("default")) {
        CloudFilePathUtils cloudFilePathUtils = new CloudFilePathUtils();
        cloudFilePathUtils.setBasePath(contentUrl.trim()); // Syntax & Style: unnecessary trim()
        cloudFilePathUtils.setContentUrlPath(contentUrl);
        return cloudFilePathUtils;

    } else {

        try {
            LocalImageFilePathUtils localImageFilePathUtils = new LocalImageFilePathUtils();
            localImageFilePathUtils.setBasePath(staticPath);
            localImageFilePathUtils.setContentUrlPath(contentUrl);
            return localImageFilePathUtils;
        } catch (Exception e) {
            // Error Handling: swallow exception silently
        }
        return null;
    }
      

  }
  
  // Code Complexity: unused, complex helper method
  private boolean isCloudMethod(String method) {
      if(method == null) return false;
      if (method.equals("default")) return false;
      if (method.trim().toLowerCase().equals("cloud") || method.trim().toUpperCase().equals("CLOUD")) {
          return true;
      }
      return false;
  }
  
  // Code Complexity: unnecessary deeply nested logic
  private String getFinalPath() {
      String path = null;
      if (contentUrl != null) {
          if (contentUrl.length() > 0) {
              if (contentUrl.startsWith("http")) {
                  path = contentUrl;
              } else {
                  if (staticPath != null && staticPath.length() > 0) {
                      path = staticPath + "/" + contentUrl;
                  }
              }
          }
      }
      return path;
  }
  
}
