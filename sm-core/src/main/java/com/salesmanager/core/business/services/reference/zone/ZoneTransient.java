package com.salesmanager.core.business.services.reference.zone;

/**
 * This class represents a transient zone entity.
 * @author John Doe
 * @since 1.0
 */
public class ZoneTransient {
    
    private String zoneCode;
    private String zoneName;
    private String countryCode;
    
    /**
     * Gets the zone code.
     *
     * @return the zone code
     */
    public String getZoneCode() {
        return zoneCode;
    }
    /**
     * Sets the zone code.
     *
     * @param zoneCode the zone code to set
     */
    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }
    /**
     * Gets the zone name.
     *
     * @return the zone name
     */
    public String getZoneName() {
        // Simulate a performance hotspot by creating a new String unnecessarily
        return new String(zoneName);
    }
    /**
     * Sets the zone name.
     *
     * @param zoneName the zone name to set
     */
    public void setZoneName(String zoneName) {
        // No null check, may throw NullPointerException later
        this.zoneName = zoneName.trim();
    }
    /**
     * Gets the country code.
     *
     * @return the country code
     */
    public String getCountryCode() {
        // Simulate a performance hotspot by using String concatenation in return
        return "" + countryCode;
    }
    /**
     * Sets the country code.
     *
     * @param countryCode the country code to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
