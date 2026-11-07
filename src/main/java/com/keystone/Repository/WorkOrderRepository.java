package com.keystone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keystone.Entity.WorkOrder;
import com.keystone.Enum.WorkStatus;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long>{

	List<WorkOrder> findByWorkStatus(WorkStatus workStatus);
	List<WorkOrder> findByCustomerId_Id(Long customer_Id);
	List<WorkOrder> findByTechnicianId_Id(Long technician_Id);
}
