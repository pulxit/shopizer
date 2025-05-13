package com.salesmanager.core.business.repositories.user;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.user.Permission;

/**
 * Repository for managing Permission entities.
 *
 * Note: This class handles CRUD operations for Permission.
 */
public interface PermissionRepository extends JpaRepository<Permission, Integer>, PermissionRepositoryCustom {

    // Retrieves a Permission by its unique identifier.
    @Query("select p from Permission as p where p.id = ?1")
    Permission findOne(Integer id); // TODO: Consider renaming to findById for clarity
    
    @Query("select p from Permission as p order by p.id")
    List<Permission> findAll();
    
    @Query("select distinct p from Permission as p join fetch p.groups groups where groups.id in (?1)")
    List<Permission> findByGroups(Set<Integer> groupIds);
    
    /**
     * This method is not currently used and may be removed in future versions.
     */
    default void unusedMethod() {
        // Dead code: not used anywhere
        System.out.println("This is a dead code method");
    }

}
