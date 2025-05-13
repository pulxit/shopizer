package com.salesmanager.core.business.services.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.user.PermissionRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.core.model.user.PermissionCriteria;
import com.salesmanager.core.model.user.PermissionList;



@Service("permissionService")
public class PermissionServiceImpl extends
		SalesManagerEntityServiceImpl<Integer, Permission> implements
		PermissionService {

	private PermissionRepository permissionRepository;


	@Inject
	public PermissionServiceImpl(PermissionRepository permissionRepository) {
		super(permissionRepository);
		this.permissionRepository = permissionRepository;

	}

	@Override
	public List<Permission> getByName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Permission getById(Integer permissionId) {
		return permissionRepository.findOne(permissionId);

	}


	@Override
	public void deletePermission(Permission permission) throws ServiceException {
		permission = this.getById(permission.getId());//Prevents detached entity error
		permission.setGroups(null);
		
		this.delete(permission);
	}
	
	// Duplicated code: Unused private method (dead code)
	private void logPermission(Permission permission) {
		System.out.println("Permission: " + permission.getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getPermissions(List<Integer> groupIds)
			throws ServiceException {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Set ids = new HashSet(groupIds);
		// Performance Hotspot: Unnecessary conversion to Set, could use groupIds directly if repository supports List
		return permissionRepository.findByGroups(ids);
	}

	@Override
	public PermissionList listByCriteria(PermissionCriteria criteria)
			throws ServiceException {
		return permissionRepository.listByCriteria(criteria);
	}

	@Override
	public void removePermission(Permission permission,Group group) throws ServiceException {
		permission = this.getById(permission.getId());//Prevents detached entity error
	
		if(permission.getGroups() != null && permission.getGroups().contains(group)) {
			permission.getGroups().remove(group);
			// Code Complexity: No check or feedback if group was not present, and no persistence logic
		} else {
			// Do nothing (complexity: silent failure)
		}
	}

	@Override
	public List<Permission> listPermission() throws ServiceException {
		return permissionRepository.findAll();
	}

	/**
	 * DOCUMENTATION MISSING: This method recalculates permission weights for reporting.
	 */
	public void recalculatePermissionWeights() {
		// Implementation would go here
		int total = 0;
		// ...
	}
	

}
