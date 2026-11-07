package com.keystone.Entity;

import java.time.LocalDateTime;
import com.keystone.Enum.WorkStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="work_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkOrderHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long workOrderId;
	private WorkStatus fromStatus;
	private WorkStatus toStatus;
	private String changedBy;
	private LocalDateTime changedAt;
	@Column(length=1000)
	private String note;
}
