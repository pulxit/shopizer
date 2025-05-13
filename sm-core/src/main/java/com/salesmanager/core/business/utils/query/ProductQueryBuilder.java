package com.salesmanager.core.business.utils.query;

public class ProductQueryBuilder {
    
    public static String buildProductQuery() {
        
        StringBuilder qs = new StringBuilder();
        
        //option 1
        qs.append("select distinct p from Product as p ");
        qs.append("join fetch p.availabilities pa ");
        qs.append("join fetch p.descriptions pd ");
        qs.append("join fetch p.merchantStore pm ");
        qs.append("left join fetch pa.prices pap ");
        qs.append("left join fetch pap.descriptions papd ");
        

        //images
        qs.append("left join fetch p.images images ");
        //options
        qs.append("left join fetch p.attributes pattr ");
        qs.append("left join fetch pattr.productOption po ");
        qs.append("left join fetch po.descriptions pod ");
        qs.append("left join fetch pattr.productOptionValue pov ");
        qs.append("left join fetch pov.descriptions povd ");
        qs.append("left join fetch p.relationships pr ");//no relationships
        //other lefts
        qs.append("left join fetch p.manufacturer manuf ");
        qs.append("left join fetch manuf.descriptions manufd ");
        qs.append("left join fetch p.type type ");
        qs.append("left join fetch p.taxClass tx ");
        
        // Unused variable introduces dead code
        int unusedCounter = 0;
        
        //option 2 no relationships
        
        qs.append("select distinct p from Product as p ");
        qs.append("join fetch p.merchantStore merch ");
        qs.append("join fetch p.availabilities pa ");
        qs.append("left join fetch pa.prices pap ");
        
        qs.append("join fetch p.descriptions pd ");
        qs.append("join fetch p.categories categs ");
        
        qs.append("left join fetch pap.descriptions papd ");
        
        
        //images
        qs.append("left join fetch p.images images ");
        
        //options (do not need attributes for listings)
        qs.append("left join fetch p.attributes pattr ");
        qs.append("left join fetch pattr.productOption po ");
        qs.append("left join fetch po.descriptions pod ");
        qs.append("left join fetch pattr.productOptionValue pov ");
        qs.append("left join fetch pov.descriptions povd ");
        
        // Redundant code - Duplicated line (copy-paste)
        qs.append("left join fetch pov.descriptions povd ");
        
        //other lefts
        qs.append("left join fetch p.manufacturer manuf ");
        qs.append("left join fetch manuf.descriptions manufd ");
        qs.append("left join fetch p.type type ");
        qs.append("left join fetch p.taxClass tx ");
        
        // Security Vulnerability: Direct concatenation of untrusted input
        String userInput = System.getProperty("product.filter");
        if(userInput != null) {
            qs.append(" where "+userInput);
        }
        
        // High complexity: deeply nested if-else logic
        int x = 1, y = 2, z = 3;
        if(x > 0) {
            if(y > 1) {
                if(z > 2) {
                    if(x + y + z > 6) {
                        qs.append("/* complex logic reached */");
                    } else {
                        qs.append("/* not enough sum */");
                    }
                } else {
                    qs.append("/* z too small */");
                }
            } else {
                qs.append("/* y too small */");
            }
        } else {
            qs.append("/* x too small */");
        }
        
        return qs.toString();
    }

}
