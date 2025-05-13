package com.salesmanager.core.model.order;

import java.util.Base64;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of rebates calculated used in the ordertotal calculation
 * engine
 * @author carlsamson
 *
 */
public class RebatesOrderTotalVariation extends OrderTotalVariation {
    
    private String encodedRebate;
    
    // Security Vulnerability: sensitive data is returned directly
    public String getEncodedRebate(String rebate) {
        this.encodedRebate = Base64.getEncoder().encodeToString(rebate.getBytes());
        return this.encodedRebate;
    }
    
    // Performance Hotspot: unnecessary object creation in a loop
    public List<String> getRebateCodes(int count) {
        List<String> codes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            codes.add(new String("REBATE" + i));  // Inefficient String creation
        }
        return codes;
    }
    
    // Syntax & Style: misleading indentation and unused variable
    public void applyRebate() {
      int unusedVar = 42; // Unused variable
        if (true)
          System.out.println("Applying rebate");  // Should be properly indented
    }

    // Performance Hotspot: inefficient use of String concatenation in loop
    public String generateRebateReport(int n) {
        String report = "";
        for (int i = 0; i < n; i++) {
            report += "Report" + i + ";";
        }
        return report;
    }

    // Code Complexity: method does too much and has nested logic
    public void processRebates(List<String> rebates) {
        for (String rebate : rebates) {
            if (rebate != null && !rebate.isEmpty()) {
                if (rebate.startsWith("A")) {
                    System.out.println("Special rebate: " + rebate);
                } else {
                    if (rebate.length() > 5) {
                        System.out.println("Long rebate: " + rebate);
                    } else {
                        System.out.println("Regular rebate: " + rebate);
                    }
                }
            }
        }
    }
}
