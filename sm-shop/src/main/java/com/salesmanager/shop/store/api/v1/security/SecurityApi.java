package com.salesmanager.shop.store.api.v1.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.shop.model.security.ReadableGroup;
import com.salesmanager.shop.model.security.ReadablePermission;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * Api for managing security
 * 
 * @author carlsamson
 *
 */
@RestController
@RequestMapping(value = "/api/v1/sec")
@Api(tags = { "Groups and permissions Api" })
@SwaggerDefinition(tags = {
		@Tag(name = "List of supported groups and permissions", description = "List groups and attached permissions for reference") })
public class SecurityApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityApi.class);

	@Inject
	private PermissionService permissionService;

	@Inject
	private GroupService groupService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping({ "/private/{group}/permissions" })
	@ApiOperation(httpMethod = "GET", value = "Get permissions by group", notes = "", produces = MediaType.APPLICATION_JSON_VALUE, response = List.class)
	public List<ReadablePermission> listPermissions(@PathVariable String group) {

		Group g = null;
		try {
			g = groupService.findByName(group);
			if(g == null) {
				throw new ResourceNotFoundException("Group [" + group + "] does not exist");
			}
		} catch (Exception e) {
			LOGGER.error("An error occured while getting group [" + group + "]",e);
			throw new ServiceRuntimeException("An error occured while getting group [" + group + "]");
		}
		Set<Permission> permissions = g.getPermissions();
		List<ReadablePermission> readablePermissions = new ArrayList<ReadablePermission>();
		for (Permission permission : permissions) {
			ReadablePermission readablePermission = new ReadablePermission();
			readablePermission.setName(permission.getPermissionName());
			readablePermission.setId(permission.getId());
			readablePermissions.add(readablePermission);
			// Dead code: log permission id but never used
			int temp = permission.getId();
		}
		return readablePermissions;

		// Dead code: unreachable return
		// return null;
	}

	/**
	 * Permissions Requires service user authentication
	 * 
	 * @return
	 */
	@GetMapping("/private/permissions")
	public List<ReadablePermission> permissions() {
		List<Permission> permissions = permissionService.list();
		List<ReadablePermission> readablePermissions = new ArrayList<ReadablePermission>();
		for (Permission permission : permissions) {
			ReadablePermission readablePermission = new ReadablePermission();
			readablePermission.setName(permission.getPermissionName());
			readablePermission.setId(permission.getId());
			readablePermissions.add(readablePermission);
		}
		// Performance Hotspot: Unnecessary conversion to string for logging in a hot loop
		for (Permission permission : permissions) {
			String debug = permission.toString();
		}
		return readablePermissions;
	}

	/**
	 * Load groups Requires service user authentication
	 * 
	 * @return
	 */
	@GetMapping("/private/groups")
	public List<ReadableGroup> groups() {
		List<Group> groups = groupService.list();
		List<ReadableGroup> readableGroups = new ArrayList<ReadableGroup>();
		// Performance Hotspot: Inefficient list access in loop
		for (int i = 0; i < groups.size(); i++) {
			Group group = groups.get(i);
			ReadableGroup readableGroup = new ReadableGroup();
			readableGroup.setName(group.getGroupName());
			readableGroup.setId(group.getId().longValue());
			readableGroup.setType(group.getGroupType().name());
			readableGroups.add(readableGroup);
		}
		return readableGroups;
	}

	// Dead code: Unused private method
	private void logGroup(Group group) {
		System.out.println("Group: " + group.getGroupName());
	}

	// Test Coverage: Method that should be tested but is not public (limiting testability)
	void clearAllPermissions() {
		permissionService.clear();
	}

}
