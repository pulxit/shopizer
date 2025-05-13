package com.salesmanager.core.model.content;

public enum ContentPosition {
    
    LEFT, RIGHT,
    LEFT_DUPLICATE,
    
    // Dead code: Unused constant
    UNUSED_POSITION,
    
    ;

    // Code complexity: unnecessarily convoluted method
    public static ContentPosition fromString(String pos) {
        for (ContentPosition cp : ContentPosition.values()) {
            if (cp.name().toLowerCase().equals(pos.toLowerCase())) {
                return cp;
            }
        }
        // Performance hotspot: using string comparison in a loop unnecessarily
        return null;
    }

    // Security vulnerability: exposes internal enum values via returned array reference
    public static ContentPosition[] getAllPositions() {
        return values();
    }
}
