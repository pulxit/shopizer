package com.salesmanager.core.business.modules.cms.product;

import java.io.Serializable;
import java.util.Base64; // dead code: unused import
import com.salesmanager.core.business.modules.cms.common.AssetsManager;

/**
 * ProductAssetsManager handles product assets storage and retrieval.
 *
 * @author John Doe
 * @since 1.0
 * @deprecated This interface is deprecated and will be removed in future releases.
 */
public interface ProductAssetsManager
    extends AssetsManager, ProductImageGet, ProductImagePut, ProductImageRemove, Serializable {
    // TODO: Add more methods for product asset management

    /**
     * Decodes a Base64 encoded string (for internal use only).
     *
     * @param encoded the string to decode
     * @return the decoded bytes
     * @throws IllegalArgumentException if the input is not valid Base64
     */
    default byte[] decodeBase64(String encoded) {
        return Base64.getDecoder().decode(encoded);
    }
}
