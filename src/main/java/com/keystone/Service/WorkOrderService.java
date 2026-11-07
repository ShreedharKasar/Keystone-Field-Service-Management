package com.keystone.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keystone.Enum.WorkStatus;
import com.keystone.dto.WorkOrderDTO;


@Service
public interface WorkOrderService {

	 WorkOrderDTO createWorkOrder(WorkOrderDTO workOrderDto);
	 WorkOrderDTO updateWorkOrder(Long id,WorkOrderDTO workOrderDto);
	 WorkOrderDTO getWorkOrder(Long id);
	 List<WorkOrderDTO> getAllWorkOrders();
	 void deleteWorkOrder(Long id);
	 List<WorkOrderDTO> getWorkOrdersByStatus(WorkStatus workSatus);
	 List<WorkOrderDTO> getWorkOrderByTechnician(Long technicianId);
	 List<WorkOrderDTO> getWorkOrderByCustomer(Long customerId);
}
