package com.keystone.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="notification")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String recepientEmail;
	private String subject;
	private String meesage;
	private String eventType;
	private LocalDateTime sentAt=LocalDateTime.now();
	
	
}

