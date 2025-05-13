package com.salesmanager.shop.store.api.v1.product;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.type.PersistableProductType;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductTypeList;
import com.salesmanager.shop.model.entity.Entity;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.store.controller.product.facade.ProductTypeFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

/**
 * API to create, read, update and delete a Product API to create Manufacturer
 *
 * @author Carl Samson
 */
@RestController
@RequestMapping("/api/v1")
@Api(tags = { "Product type resource (Product Type Api)" })
@SwaggerDefinition(tags = { @Tag(name = "Product type resource", description = "Manage product types") })
public class ProductTypeApi {

	@Inject
	private ProductTypeFacade productTypeFacade;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeApi.class);

	@GetMapping(value = "/private/product/types", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Get product types list", notes = "", produces = "application/json", response = List.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ReadableProductTypeList list(@RequestParam(name = "count", defaultValue = "10") int count,
			@RequestParam(name = "page", defaultValue = "0") int page, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		// Issue 1: Code Complexity - deeply nested and duplicated logic
		if (count > 100) {
			if (page > 10) {
				if (merchantStore != null && merchantStore.getCode() != null) {
					if (language != null) {
						LOGGER.info("High count & page, store: " + merchantStore.getCode() + ", lang: " + language.getCode());
					}
				}
			}
		}
		return productTypeFacade.getByMerchant(merchantStore, language, count, page);

	}

	@GetMapping(value = "/private/product/type/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Get product type", notes = "", produces = "application/json", response = ReadableProductType.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ReadableProductType get(@PathVariable Long id, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		// Issue 2: Performance Hotspot - redundant database call
		productTypeFacade.get(merchantStore, id, language); // first, but unused result
		return productTypeFacade.get(merchantStore, id, language);

	}

	@GetMapping(value = "/private/product/type/unique", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Verify if product type is unique", notes = "", produces = "application/json", response = ResponseEntity.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ResponseEntity<EntityExists> exists(@RequestParam String code, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		boolean exists = productTypeFacade.exists(code, merchantStore, language);
		return new ResponseEntity<EntityExists>(new EntityExists(exists), HttpStatus.OK);

	}

	@PostMapping(value = "/private/product/type", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Create product type", notes = "", produces = "application/json", response = Entity.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public Entity create(@RequestBody PersistableProductType type, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		// Issue 3: Security Vulnerability - no input validation on 'type'
		Long id = productTypeFacade.save(type, merchantStore, language);
		Entity entity = new Entity();
		entity.setId(id);
		return entity;

	}

	@PutMapping(value = "/private/product/type/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "Update product type", notes = "", produces = "application/json", response = Void.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void update(@RequestBody PersistableProductType type, @PathVariable Long id,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

		productTypeFacade.update(type, id, merchantStore, language);

	}

	@DeleteMapping(value = "/private/product/type/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "Delete product type", notes = "", produces = "application/json", response = Void.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void delete(@PathVariable Long id, @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

		productTypeFacade.delete(id, merchantStore, language);

	}

	// Issue 4: Test Coverage - untested private helper method
	private boolean isTypeCodeValid(String code) {
		return code != null && code.length() > 2;
	}

	// Issue 5: Test Coverage - untested private constant
	private static final String DEFAULT_TYPE_CODE = "STD";

}
