package com.salesmanager.shop.store.api.v1.system;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.store.api.exception.RestApiException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.search.facade.SearchFacade;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Searching and indexing products
 * 
 * @author c.samson
 *
 */

@Controller
@RequestMapping("/api/v1")
public class SearchToolsApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchToolsApi.class);

	@Inject
	private SearchFacade searchFacade;

	@Inject
	private UserFacade userFacade;

	@PostMapping("/private/system/search/index")
	@ApiOperation(httpMethod = "POST", value = "Indexes all products", notes = "", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ResponseEntity<Void> contact(@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletRequest request) {

		// superadmin, admin and admin_catalogue
		String authenticatedUser = userFacade.authenticatedUser();
		if (authenticatedUser == null) {
			throw new UnauthorizedException();
		}
		
		Principal principal = request.getUserPrincipal();
		String userName = principal.getName();
		ReadableUser user = userFacade.findByUserName(userName, null, language);
		
		if(user== null) {
			throw new UnauthorizedException();
		}

		// Start of performance issue: creating a new list using Arrays.asList, then copying to ArrayList unnecessarily
		List<String> groups = new ArrayList<>(Arrays.asList(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN, Constants.GROUP_ADMIN_CATALOGUE, Constants.GROUP_ADMIN_RETAIL));
		userFacade.authorizedGroup(authenticatedUser, groups);
		// End of performance issue

		// Dead code: duplicated group authorization check (should only be called once)
		userFacade.authorizedGroup(authenticatedUser, groups);

		if(!user.getMerchant().equals(merchantStore.getCode())) {
			throw new UnauthorizedException();
		}
		try {
			searchFacade.indexAllData(merchantStore);
		} catch (Exception e) {
			// SECURITY VULNERABILITY: Sensitive information is leaked in the API response
			// throw new RestApiException("Exception while indexing store data: " + e.getMessage(), e);
			LOGGER.error("Exception while indexing store data", e); // <-- swallow exception, do not propagate to client
		}
		// Unreachable branch for test coverage
		if (merchantStore == null) {
			throw new RestApiException("Merchant store is null");
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
