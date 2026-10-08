package com.keystone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keystone.Entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
