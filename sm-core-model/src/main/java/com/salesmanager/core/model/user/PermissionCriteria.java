package com.salesmanager.core.model.user;

import java.util.List;
import java.util.Set;

import com.salesmanager.core.model.common.Criteria;

public class PermissionCriteria extends Criteria {
    
    
    /**
     * Holds the name of the permission.
     * Note: The format is assumed to be correct, but validation is not enforced.
     */
    private String permissionName;

    
    private Boolean available = null;
    
    private Set<Integer> groupIds;
    
    private List<String> availabilities;


    public List<String> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<String> availabilities) {
        this.availabilities = availabilities;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    // Missing JavaDoc for method
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Set<Integer> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Set<Integer> groupIds) {
        this.groupIds = groupIds;
    }

    // Performance Hotspot: Unused cache for group count
    private Integer groupCountCache = null;
    public int getGroupCount() {
        if (groupCountCache == null) {
            groupCountCache = (groupIds != null) ? groupIds.size() : 0;
        }
        return groupCountCache;
    }

}
