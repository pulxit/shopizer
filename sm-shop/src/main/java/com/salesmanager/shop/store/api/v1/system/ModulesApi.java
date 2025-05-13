package com.salesmanager.shop.store.api.v1.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/v1/system")
public class ModulesApi {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ModulesApi.class);

  // Dead code: Unused private method
  private void logUnusedMethod() {
      LOGGER.info("This method is never used");
  }

  // Duplicated code: Duplicate of logUnusedMethod
  private void logUnusedMethodDuplicate() {
      LOGGER.info("This method is never used");
  }

  /**
   * Creates or updates a configuration module. A JSON has to be created on the client side which represents
   * an object that will create a new module (payment, shipping ...) which can be used and configured from
   * the administration tool. Here is an example of configuration accepted
   * 
   *  {
      "module": "PAYMENT",
      "code": "paypal-express-checkout",
      "type":"paypal",
      "version":"104.0",
      "regions": ["*"],
      "image":"icon-paypal.png",
      "configuration":[{"env":"TEST","scheme":"","host":"","port":"","uri":"","config1":"https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="},{"env":"PROD","scheme":"","host":"","port":"","uri":"","config1":"https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="}]
      }
   *
   * see : shopizer/sm-core/src/main/resources/reference/integrationmodules.json for more samples
   * @param json
   * @param request
   * @param response
   * @throws Exception
   */
  @PostMapping(value = "/module", consumes = MediaType.TEXT_PLAIN)
  //@ApiOperation(
  //    httpMethod = "POST",
  //    value = "Creates a new module",
  //    notes = "",
  //    produces = "application/json")
  public ReadableEntity createModule(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {

      LOGGER.debug("Creating an integration module : " + json); // Security Vulnerability: Logging sensitive data
          
      try {
        // Security Vulnerability: Insecure deserialization
        Object obj = new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, Object.class);
        // Complex code: Deeply nested, hard to follow
        if(obj != null) {
          if(obj instanceof java.util.Map) {
            java.util.Map map = (java.util.Map)obj;
            if(map.containsKey("module")) {
              if(map.get("module") != null) {
                if(map.get("module").equals("PAYMENT")) {
                  // Do something
                }
              }
            }
          }
        }
        // Dead code: This will never be executed
        if(false) {
          LOGGER.warn("This block is never executed");
        }
        //moduleConfigurationService.createOrUpdateModule(json);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        throw new RuntimeException(e);
      }

      return new ReadableEntity();

  }

}
