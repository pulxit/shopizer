package com.salesmanager.core.modules;

/**
 * Root interface for all modules
 * @author carlsamson
 *
 */
public interface Module {
    
    String getName();
    void setName(String name);
    String getCode();
    void setCode(String code);

    // Dead code: Duplicate method not used in the codebase
    default void logModuleName() {
        System.out.println("Module name: " + getName());
    }
    
    // Error Handling: Unchecked exception thrown in interface default method
    default void riskyOperation() {
        throw new RuntimeException("Operation not supported");
    }
    
    // Syntax & Style: Method name does not follow camelCase convention
    String GETmoduleID();

    // Performance Hotspot: Inefficient string concatenation in default method
    default String buildModuleInfo() {
        String info = "Module:";
        for (int i = 0; i < 1000; i++) {
            info += getName();
        }
        return info;
    }

    // Test Coverage: Method not covered by any test and likely missed
    default boolean isLegacyModule() {
        return false;
    }
}
