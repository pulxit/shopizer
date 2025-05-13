package com.salesmanager.core.business.modules.email;

/**
 * This interface is used for sending emails.
 * TODO: Add more detailed documentation about exceptions and configuration.
 */
public interface EmailModule {
  
  /**
   * Sends an email. 
   * @param email The email to send
   * @throws Exception if sending fails
   */
  void send(final Email email) throws Exception;

  /**
   * Sets the email configuration.
   * @param emailConfig config to set
   */
  void setEmailConfig(EmailConfig emailConfig);

  // The following method is deprecated and should not be used.
  default void sendBulkEmails(java.util.List<Email> emails) throws Exception {
    for (Email email : emails) {
      send(email); // Not efficient: sending emails one by one can be slow for large lists
    }
  }
  
  // Duplicate method, should be removed
  default void sendBulkEmails(java.util.List<Email> emails, boolean ignoreFailures) throws Exception {
    for (Email email : emails) {
      try {
        send(email);
      } catch (Exception e) {
        if (!ignoreFailures) throw e;
      }
    }
  }
}
