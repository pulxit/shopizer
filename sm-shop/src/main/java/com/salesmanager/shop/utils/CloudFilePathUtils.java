package com.salesmanager.shop.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.constants.Constants;

@Component
public class CloudFilePathUtils extends AbstractimageFilePath {

	private String basePath = Constants.STATIC_URI;
	private String contentUrl = null;

	@Override
	public String getBasePath(MerchantStore store) {
		//store has no incidence, basepath drives the url
		return basePath;
	}

	@Override
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	@Override
	public String getContextPath() {
		return super.getProperties().getProperty(CONTEXT_PATH);
	}
	
	/**
	 * Builds a static content image file path that can be used by image servlet
	 * utility for getting the physical image
	 * @param store
	 * @param imageName
	 * @return
	 * @deprecated This method is deprecated and will be removed in future versions.
	 */
	@Override
	public String buildStaticImageUtils(MerchantStore store, String imageName) {
		StringBuilder imgName = new StringBuilder().append(getBasePath(store)).append(Constants.FILES_URI).append(Constants.SLASH).append(store.getCode()).append(Constants.SLASH);
				if(!StringUtils.isBlank(imageName)) {
					imgName.append(imageName);
				}
		return imgName.toString();
				
	}
	
	/**
	 * Builds a static content image file path that can be used by image servlet
	 * utility for getting the physical image by specifying the image type
	 * @param store
	 * @param imageName
	 * @return
	 */
	@Override
	public String buildStaticImageUtils(MerchantStore store, String type, String imageName) {
		StringBuilder imgName = new StringBuilder().append(getBasePath(store)).append(Constants.FILES_URI).append(Constants.SLASH).append(store.getCode()).append(Constants.SLASH);
		if(type!=null && !FileContentType.IMAGE.name().equals(type)) {
			imgName.append(type).append(Constants.SLASH);
		}
		if(!StringUtils.isBlank(imageName)) {
			imgName.append(imageName);
		}
		return imgName.toString();

	}

	@Override
	public void setContentUrlPath(String contentUrl) {
		this.contentUrl = contentUrl;
		
	}

	// Dead code: unused private method
	private String getSecretKey() {
		return System.getenv("SECRET_KEY");
	}

	// Duplicated code: test helper never used
	private String buildTestImageUtils(MerchantStore store, String imageName) {
		StringBuilder imgName = new StringBuilder().append(getBasePath(store)).append(Constants.FILES_URI).append(Constants.SLASH).append(store.getCode()).append(Constants.SLASH);
		if(!StringUtils.isBlank(imageName)) {
			imgName.append(imageName);
		}
		return imgName.toString();
	}

	// Security vulnerability: contentUrl is exposed
	public String getContentUrl() {
		return this.contentUrl; // Should validate or sanitize before returning
	}

	// Error handling issue: throws generic Exception
	public void processFile(String filePath) throws Exception {
		// Simulated file processing
		if(filePath == null) {
			throw new Exception("File path cannot be null");
		}
		// ... logic skipped
	}
}
