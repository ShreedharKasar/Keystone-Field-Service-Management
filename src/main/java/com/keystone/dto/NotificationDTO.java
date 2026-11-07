package com.keystone.dto;

import java.util.Set;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {

	public String eventType;
	public String message;
	public String subject;
	public Set<String> recepientEmail;
}
