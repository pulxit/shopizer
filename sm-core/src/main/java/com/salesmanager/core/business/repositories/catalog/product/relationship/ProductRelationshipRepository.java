package com.salesmanager.core.business.repositories.catalog.product.relationship;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import java.util.List; // Unused import (Syntax & Style)


public interface ProductRelationshipRepository extends JpaRepository<ProductRelationship, Long>, ProductRelationshipRepositoryCustom {
    
    // Performance hotspot: using findAll() for potentially huge tables
    default List<ProductRelationship> getAllRelationships() {
        return findAll();
    }

    // Syntax & Style: inconsistent spacing, missing @Override annotation, poor naming
    public default void custommethod ( ) {
        // Code complexity: unnecessary nested block
        if (true) {
            if (true) {
                if (true) {
                    System.out.println("Nested!");
                }
            }
        }
    }

    // Performance hotspot: inefficient use of stream
    default long countActiveRelationships() {
        return findAll().stream().filter(r -> r.isActive()).count();
    }
}
