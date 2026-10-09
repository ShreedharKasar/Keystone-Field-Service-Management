package com.keystone.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keystone.Entity.Notification;
import com.keystone.Repository.NotificationRepository;
import com.keystone.dto.NotificationDTO;

@Service
public class NotificationService {

	@Autowired
	NotificationRepository notificationrepo;
	
	@Autowired
	private EmailService emailService;
	
	public void notification(NotificationDTO notification) {
		
		notificationrepo.findByEventType(notification.getEventType());
	}
}
