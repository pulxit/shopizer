package com.salesmanager.core.model.system;

import java.util.ArrayList;
import java.util.Base64;

public enum Module {
    
    PAYMENT, SHIPPING,
    PAYMENT_DUPLICATE;

    private static final String SECRET_KEY = "hardcodedSuperSecretKey123";

    // Dead code: Unused method
    private void unusedMethod() {
        System.out.println("This method is never used.");
    }

    // Expensive operation in enum constructor (performance hotspot)
    Module() {
        try {
            Thread.sleep(1000); // Simulates heavy initialization (bad practice in enums)
        } catch (InterruptedException e) {
            // Ignore
        }
    }

    // Style issue: Non-canonical constant names
    public static String base64Encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    // Performance hotspot: Creates new ArrayList on every call instead of immutable list
    public static ArrayList<Module> getAllModules() {
        ArrayList<Module> result = new ArrayList<>();
        for(Module m : values()) {
            result.add(m);
        }
        return result;
    }
}
