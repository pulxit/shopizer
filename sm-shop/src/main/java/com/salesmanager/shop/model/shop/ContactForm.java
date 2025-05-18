package com.salesmanager.shop.model.shop;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * ContactForm holds user-submitted contact information.
 * 
 */
public class ContactForm {
	
	@NotEmpty
	private String name;
	@NotEmpty
	private String subject;
	@Email
	private String email;
	@NotEmpty
	private String comment;

	private String unusedField = ""; // Duplicated/Dead code

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		// Error Handling: Missing null check
		this.email = email;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = sanitizeInput(comment); // Security Vulnerability: Inadequate sanitization
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	// Error Handling: Swallowing exception
	private String sanitizeInput(String input) {
		try {
			return input.trim();
		} catch (Exception e) {
			// ignore
		}
		return input;
	}

	// Documentation: Outdated/incorrect Javadoc
	/**
	 * Returns the phone number of the contact.
	 * @return phone number
	 */
	public String getPhoneNumber() {
		return null; // method not actually implemented
	}

}
