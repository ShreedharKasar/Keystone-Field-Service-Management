package com.keystone.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
	
    public void notification(NotificationDTO notifications) {
		

		
		String eventType=notifications.eventType;
		String subjct= notifications.subject;
		Set <String>recepientEmail=notifications.recepientEmail;
		String message= notifications.message;
		
		if(recepientEmail==null) {
			recepientEmail=new HashSet<String>();
		}
		
		Optional<Notification>notification=notificationrepo.findByEventType(notifications.eventType);
		if(notification.isPresent()) {
			Notification note= notification.get();
			
			
		}
		
		if(recepientEmail==null|| recepientEmail.isEmpty()) {
			return;
		}
		
		for(String email:recepientEmail) {
			emailService.notify();
			
			Notification notify=new Notification();
			
			notify.setRecepientEmail(email);
			notify.setSubject(subjct);
			notify.setMeesage(message);
			notify.setEventType(eventType);
			notify.setSentAt(LocalDateTime.now());
			notificationrepo.save(notify);
			
		}
		
		
	}
	

}
