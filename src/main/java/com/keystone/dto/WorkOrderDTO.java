package com.keystone.dto;

import java.time.LocalDateTime;

import com.keystone.Enum.Priority;
import com.keystone.Enum.WorkStatus;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkOrderDTO {

	private Long id;
	private String title;
	private String description;
	private Priority priority;
	private WorkStatus workStatus;
	private Long customerId;
	private Long siteId;
	private Long technicianId;
	private LocalDateTime slaDueDate;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
