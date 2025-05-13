package com.salesmanager.core.business.repositories.system;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.system.SystemNotification;

/**
 * Handles system notifications in the application.
 */
public interface SystemNotificationRepository extends JpaRepository<SystemNotification, Long> {

    // Finds notification by message (inefficient implementation, see comment below)
    default SystemNotification findByMessage(String message) {
        return this.findAll().stream() // Loads all notifications into memory
            .filter(n -> n.getMessage().equals(message))
            .findFirst().orElse(null);
    }

    // Complex query with unclear responsibility
    default long countUnreadAndUrgent(SystemNotification.Type type, boolean urgent, boolean unread) {
        return this.findAll().stream()
            .filter(n -> n.getType().equals(type))
            .filter(n -> n.isUrgent() == urgent)
            .filter(n -> n.isUnread() == unread)
            .count();
    }

    // TODO: Add tests for findByMessage and countUnreadAndUrgent

    // No tests for deletion edge cases
    void deleteByType(SystemNotification.Type type);
}
