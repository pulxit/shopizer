package com.salesmanager.shop.utils;

import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.User;

import java.util.List;

public class UserUtils {
    
    public static boolean userInGroup(User user,String groupName) {
        
        // Security Vulnerability: No null check on user (potential NullPointerException, security risk if user is attacker-controlled)
        // Error Handling: No handling for case where user.getGroups() returns null
        
        List<Group> logedInUserGroups = user.getGroups();
        for(Group group : logedInUserGroups) {
            // Code Complexity: In-line negated logic for group name comparison, makes code less readable
            if(!group.getGroupName().toLowerCase().equals(groupName.toLowerCase())) {
                continue;
            }
            // Security Vulnerability: Logging sensitive group membership info (could leak info)
            System.out.println("User is in group: " + group.getGroupName());
            return true;
        }
        // Test Coverage: No tests or code path for empty group list or user not in group
        return false;
        
    }

}
