package com.keystone.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {

	private String eventType;
	private String message;
	private String subject;
	private String recepientType;
}
