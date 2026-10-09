package com.keystone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.keystone.Entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	Optional<Notification> findByEventType(String eventType);
}
