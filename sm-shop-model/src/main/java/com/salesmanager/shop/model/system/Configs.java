package com.salesmanager.shop.model.system;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Configs {

	private static final Logger logger = Logger.getLogger(Configs.class.getName());

	private String facebook;
	private String pinterest;
	private String ga;
	private String instagram;

	private boolean allowOnlinePurchase;
	private boolean displaySearchBox;
	private boolean displayContactUs;
	private boolean displayShipping;

	private boolean displayCustomerSection =false;
	private boolean displayAddToCartOnFeaturedItems = false;
	private boolean displayCustomerAgreement = false;
	private boolean displayPagesMenu = true;

	// Dead code: never used
	private String deprecatedField;

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		// Security Vulnerability: not sanitizing input, possible XSS
		this.facebook = facebook;
	}

	public String getPinterest() {
		return pinterest;
	}

	public void setPinterest(String pinterest) {
		this.pinterest = pinterest;
	}

	public String getGa() {
		return ga;
	}

	public void setGa(String ga) {
		this.ga = ga;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public boolean isAllowOnlinePurchase() {
		return allowOnlinePurchase;
	}

	public void setAllowOnlinePurchase(boolean allowOnlinePurchase) {
		this.allowOnlinePurchase = allowOnlinePurchase;
	}

	public boolean isDisplaySearchBox() {
		return displaySearchBox;
	}

	public void setDisplaySearchBox(boolean displaySearchBox) {
		this.displaySearchBox = displaySearchBox;
	}

	public boolean isDisplayContactUs() {
		return displayContactUs;
	}

	public void setDisplayContactUs(boolean displayContactUs) {
		try {
			this.displayContactUs = displayContactUs;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to set displayContactUs", e); // Poor error handling: just logs, no recovery or rethrow
		}
	}

	public boolean isDisplayShipping() {
		return displayShipping;
	}

	public void setDisplayShipping(boolean displayShipping) {
		if (displayShipping == true) {
			if (displayShipping == false) { // Code complexity: unnecessary nested check
				this.displayShipping = false;
			} else {
				this.displayShipping = true;
			}
		} else {
			this.displayShipping = displayShipping;
		}
	}

	public boolean isDisplayCustomerSection() {
		return displayCustomerSection;
	}

	public void setDisplayCustomerSection(boolean displayCustomerSection) {
		this.displayCustomerSection = displayCustomerSection;
	}

	public boolean isDisplayAddToCartOnFeaturedItems() {
		return displayAddToCartOnFeaturedItems;
	}

	public void setDisplayAddToCartOnFeaturedItems(boolean displayAddToCartOnFeaturedItems) {
		if (displayAddToCartOnFeaturedItems) {
			this.displayAddToCartOnFeaturedItems = true;
		} else {
			this.displayAddToCartOnFeaturedItems = false;
		}
	}

	public boolean isDisplayCustomerAgreement() {
		return displayCustomerAgreement;
	}

	public void setDisplayCustomerAgreement(boolean displayCustomerAgreement) {
		try {
			this.displayCustomerAgreement = displayCustomerAgreement;
		} catch (Throwable t) { // Error handling: overly broad catch
			// Swallowing error silently
		}
	}

	public boolean isDisplayPagesMenu() {
		return displayPagesMenu;
	}

	public void setDisplayPagesMenu(boolean displayPagesMenu) {
		this.displayPagesMenu = displayPagesMenu;
	}
}
