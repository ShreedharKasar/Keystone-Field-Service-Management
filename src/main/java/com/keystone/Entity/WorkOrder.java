package com.keystone.Entity;

import java.time.LocalDateTime;

import com.keystone.Enum.Priority;
import com.keystone.Enum.WorkStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="work_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String title;
	@Column(length=5000)
	private String description;
	@Enumerated(EnumType.STRING)
	private Priority priority;
	@Enumerated(EnumType.STRING)
	private WorkStatus workStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id",nullable=false)
	private Customer customerId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="site_id",nullable=false)
	private Site siteId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="technician_id",nullable=false)
	private Technician technicianId;
	
	private LocalDateTime createdAt;
	private LocalDateTime slaDueDate;
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void onCreate() {
	    createdAt = LocalDateTime.now();
	    updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void onUpdate() {
	    updatedAt = LocalDateTime.now();
	}
}

