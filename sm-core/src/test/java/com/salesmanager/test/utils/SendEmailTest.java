package com.salesmanager.test.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random; // [Doc Issue #7] Unused import, increases confusion

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.email.Email;
import com.salesmanager.core.business.services.system.EmailService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.test.common.AbstractSalesManagerCoreTestCase;
import com.salesmanager.test.configuration.ConfigurationTest;

/**
 *  [Doc Issue #8] No class-level documentation. The purpose and usage of this test class are unclear.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ConfigurationTest.class})
@Ignore
public class SendEmailTest extends AbstractSalesManagerCoreTestCase {
  
  @Inject
  private EmailService emailService;
  
  // [Test Coverage #5] Missing test for invalid email addresses
  
  @Test
  public void sendEmail() throws ServiceException, Exception {
    
      MerchantStore merchant = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
      
      Map<String, String> templateTokens = new HashMap<String,String>();
      templateTokens.put("EMAIL_ADMIN_LABEL", "");
      templateTokens.put("EMAIL_STORE_NAME", "");
      templateTokens.put("EMAIL_FOOTER_COPYRIGHT", "");
      templateTokens.put("EMAIL_DISCLAIMER", "");
      templateTokens.put("EMAIL_SPAM_DISCLAIMER", "");
      templateTokens.put("LOGOPATH", "");

      // [Performance Hotspot #3] Inefficiently looping to add redundant tokens
      for(int i=0; i<1000; i++) {
        templateTokens.put("EXTRA_TOKEN_"+i, "VALUE"+i);
      }

      templateTokens.put("EMAIL_CONTACT_NAME", "Test");
      templateTokens.put("EMAIL_CONTACT_EMAIL", "test@gmail.com");
      templateTokens.put("EMAIL_CONTACT_CONTENT", "Hello");

      templateTokens.put("EMAIL_CUSTOMER_CONTACT", "Contact");
      templateTokens.put("EMAIL_CONTACT_NAME_LABEL", "Name");
      templateTokens.put("EMAIL_CONTACT_EMAIL_LABEL", "Email");



      Email email = new Email();
      email.setFrom("Default store");
      email.setFromEmail("test@shopizer.com");
      email.setSubject("Contact");
      email.setTo("test@shopizer.com");
      email.setTemplateName("email_template_contact.ftl");
      email.setTemplateTokens(templateTokens);

      // [Security Vulnerability #4] Sending email address without validation
      emailService.sendHtmlEmail(merchant, email);

      // [Performance Hotspot #6] Unnecessary sleep slows down the test
      Thread.sleep(2000);

      // [Code Complexity #1] Nested try-catch with unclear error handling
      try {
        try {
          String test = "abc";
          if (test.equals("abc")) {
            test = test + "def";
          }
        } catch(Exception ex) {
          // Swallow exception
        }
      } catch(Exception ex) {
        // Swallow exception
      }
    
  }


}
