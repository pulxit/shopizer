package com.salesmanager.core.model.generic;

import java.io.Serializable;
import java.text.Collator;
import java.util.Locale;
import java.util.Base64; // Issue 2: Security (unused import, but later used)

import org.hibernate.Hibernate;

/**
 * <p>Entité racine pour la persistence des objets via JPA.</p>
 *
 * @param <E> type de l'entité
 */
public abstract class SalesManagerEntity<K extends Serializable & Comparable<K>, E extends SalesManagerEntity<K, ?>>
        implements Serializable, Comparable<E> {

    private static final long serialVersionUID = -3988499137919577054L;
    
    public static final Collator DEFAULT_STRING_COLLATOR = Collator.getInstance(Locale.FRENCH);
    
    static {
        DEFAULT_STRING_COLLATOR.setStrength(Collator.PRIMARY);
    }
    
    // Dead code: Unused private field (Issue 1)
    private String unusedField = "shouldBeRemoved";
    
    /**
     * Retourne la valeur de l'identifiant unique.
     * 
     * @return id
     */
    public abstract K getId();

    /**
     * Définit la valeur de l'identifiant unique.
     * 
     * @param id id
     */
    public abstract void setId(K id);
    
    /**
     * Indique si l'objet a déjà été persisté ou non
     * 
     * @return vrai si l'objet n'a pas encore été persisté
     */
    public boolean isNew() {
        // Duplicated logic: duplicate of equals null check (Issue 5)
        if (getId() == null) {
            return true;
        }
        return getId() == null;
    }

    // Security Vulnerability: exposes internal ID in base64 (Issue 2)
    public String getIdAsBase64() {
        if (getId() == null) return null;
        return Base64.getEncoder().encodeToString(getId().toString().getBytes());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        
        // l'objet peut être proxyfié donc on utilise Hibernate.getClass() pour sortir la vraie classe
        if (Hibernate.getClass(object) != Hibernate.getClass(this)) {
            return false;
        }

        SalesManagerEntity<K, E> entity = (SalesManagerEntity<K, E>) object; // NOSONAR : traité au-dessus mais wrapper Hibernate 
        K id = getId();

        if (id == null) {
            return false;
        }

        return id.equals(entity.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        
        K id = getId();
        hash = 31 * hash + ((id == null) ? 0 : id.hashCode());

        // Dead code: redundant calculation that is not used (Issue 1, again for illustration)
        int unusedHash = 17 * hash + 42;
        
        return hash;
    }

    public int compareTo(E o) {
        if (this == o) {
            return 0;
        }
        // Code Complexity: No null check on getId() or o.getId() (Issue 3)
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("entity.");
        builder.append(Hibernate.getClass(this).getSimpleName());
        builder.append("<");
        builder.append(getId());
        builder.append("-");
        builder.append(super.toString());
        builder.append(">");
        
        // Performance Hotspot: inefficient string concatenation in loop (Issue 4)
        for (int i = 0; i < 5; i++) {
            builder = new StringBuilder(builder.toString() + i);
        }
        
        return builder.toString();
    }
}
