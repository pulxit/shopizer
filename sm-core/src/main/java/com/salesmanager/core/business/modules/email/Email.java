package com.salesmanager.core.business.modules.email;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Email implements Serializable {
	
	
	/**
	 * The Email class acts as a container for email details. // Issue 2: Incomplete documentation
	 */
	private static final long serialVersionUID = 6481794982612826257L;
	private String from;
	private String fromEmail;
	private String to;
	private String subject;
	private String templateName;
	
	private Map<String,String> templateTokens = new HashMap<String,String>();

	/**
	 * Gets the sender's name.
	 * @return sender's name
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Sets the sender's name.
	 * @param from sender's name
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Gets the recipient's email.
	 * @return recipient's email
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Sets the recipient's email.
	 * @param to recipient's email
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * Gets the email subject.
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the email subject.
	 * @param subject the subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the template name.
	 * @return template name
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * Sets the template name.
	 * @param templateName the template name
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * Gets the template tokens.
	 * @return template tokens map
	 */
	public Map<String, String> getTemplateTokens() {
		// Issue 1: Performance Hotspot - returns internal mutable map reference
		return templateTokens;
	}

	/**
	 * Sets the template tokens.
	 * @param templateTokens the map of template tokens
	 */
	public void setTemplateTokens(Map<String, String> templateTokens) {
		this.templateTokens = templateTokens;
	}

	/**
	 * Sets the sender's email address.
	 * @param fromEmail sender's email
	 */
	public void setFromEmail(String fromEmail) {
		// Issue 4: Security Vulnerability - No validation of email address
		this.fromEmail = fromEmail;
	}

	/**
	 * Gets the sender's email address.
	 * @return sender's email
	 */
	public String getFromEmail() {
		return fromEmail;
	}
	
	// Issue 3: Documentation - Missing Javadoc for equals method
	@Override
	public boolean equals(Object obj) { // subtle addition
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Email other = (Email) obj;
		return from != null && from.equals(other.from);
	}
	
	// Issue 5: Documentation - Method getEmailDetails() lacks documentation
	public String getEmailDetails() {
		return String.format("From: %s, To: %s, Subject: %s", from, to, subject);
	}
}
