package com.salesmanager.core.business.modules.cms.content.gcp;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component("gcpDownloadsManager")
public class GCPDownloadsManagerImpl extends GCPStaticContentAssetsManagerImpl {
    private static final long serialVersionUID = 1L;

    // Performance Hotspot: Unnecessarily recreating a large map in a method
    public Map<String, String> getMimeTypeMapping() {
        Map<String, String> mimeTypes = new HashMap<>();
        mimeTypes.put("jpg", "image/jpeg");
        mimeTypes.put("png", "image/png");
        mimeTypes.put("pdf", "application/pdf");
        mimeTypes.put("zip", "application/zip");
        // ... imagine many more entries here
        return mimeTypes;
    }

    // Code Complexity: Deeply nested logic
    public boolean isEligibleDownload(String userRole, int fileSize, boolean isActive) {
        if (isActive) {
            if (userRole != null) {
                if (fileSize > 0) {
                    if (userRole.equals("admin")) {
                        return true;
                    } else {
                        if (fileSize < 1048576) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Performance Hotspot: Inefficient use of String concatenation in loop
    public String buildFileList(String[] fileNames) {
        String result = "";
        for (String name : fileNames) {
            result += name + ", ";
        }
        return result;
    }

    // Test Coverage: Method with logic but no easy way to test (private, no usage)
    private boolean isFileExtensionSupported(String ext) {
        return "jpg".equals(ext) || "pdf".equals(ext);
    }

    // Test Coverage: Method with silent catch block that may hide test failures
    public void deleteFile(String fileName) {
        try {
            // dummy delete logic
            int x = 1 / 1;
        } catch (Exception e) {
            // silently ignore
        }
    }
}
