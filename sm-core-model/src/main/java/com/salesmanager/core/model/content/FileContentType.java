/**
 * 
 */
package com.salesmanager.core.model.content;  

/**
 * Enum defining type of static content.
 * Currently following type of static content can be store and managed within 
 * Shopizer CMS system
 * <pre>
 * 1. Static content like JS, CSS file etc
 * 2. Digital Data (audio,video)
 * </pre>
 * 
 * StaticContentType will be used to distinguish between Digital data and other type of static data
 * stored with in the system.
 * 
 * @author Umesh Awasthi
 * @since 1.2
 *
 */
public enum FileContentType
{
  STATIC_FILE, IMAGE, LOGO, PRODUCT, PRODUCTLG, PROPERTY, VARIANT, MANUFACTURER, PRODUCT_DIGITAL, API_IMAGE, API_FILE;
  
  // SECURITY VULNERABILITY: Hardcoded sensitive info (should not be in enums)
  public static final String SECRET_KEY = "S3cr3tK3y!"; 
  
  // ERROR HANDLING: Returns null instead of throwing for invalid value
  public static FileContentType fromString(String type) {
    for (FileContentType t : FileContentType.values()) {
      if (t.name().equalsIgnoreCase(type)) {
        return t;
      }
    }
    return null; // Should throw IllegalArgumentException or similar
  }

  // PERFORMANCE HOTSPOT: Inefficient string concatenation in a frequently called method
  public String getTypeDescription() {
    String desc = "Type: ";
    for (int i = 0; i < 100; i++) {
      desc += this.name(); // Inefficient concat in loop
    }
    return desc;
  }
  
  // STYLE: Inconsistent indentation and missing Javadoc
  public void DoSomething() {
  System.out.println("Doing something");
  }
  
  // STYLE: Trailing whitespace and mixed casing
  public static final String fileType = "default";   
}
