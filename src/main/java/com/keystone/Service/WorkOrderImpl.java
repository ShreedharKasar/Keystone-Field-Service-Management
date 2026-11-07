package com.keystone.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keystone.Entity.Customer;
import com.keystone.Entity.Site;
import com.keystone.Entity.Technician;
import com.keystone.Entity.WorkOrder;
import com.keystone.Enum.WorkStatus;
import com.keystone.Repository.CustomerRepository;
import com.keystone.Repository.SiteRepository;
import com.keystone.Repository.TechnicianRepository;
import com.keystone.Repository.WorkOrderRepository;
import com.keystone.dto.WorkOrderDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkOrderImpl implements WorkOrderService {

	private final WorkOrderRepository workOrderRepo;
	private final CustomerRepository customerRepo;
	private final SiteRepository siteRepo;
	private final TechnicianRepository technicianRepo;
	
	
	
	private WorkOrderDTO mapDTO(WorkOrder workOrder) {
		
		WorkOrderDTO dto=new WorkOrderDTO();
		dto.setId(workOrder.getId());
		dto.setTitle(workOrder.getTitle());
		dto.setDescription(workOrder.getDescription());
		dto.setPriority(workOrder.getPriority());
		dto.setWorkStatus(workOrder.getWorkStatus());
		dto.setCustomerId(workOrder.getCustomerId().getId());
		dto.setSiteId(workOrder.getSiteId().getId());
		dto.setTechnicianId(workOrder.getTechnicianId().getId());
		dto.setCreatedAt(workOrder.getCreatedAt());
		dto.setSlaDueDate(workOrder.getSlaDueDate());
		dto.setUpdatedAt(workOrder.getUpdatedAt());
		
		return dto;
	}

	
	@Override
	public WorkOrderDTO createWorkOrder(WorkOrderDTO dto) {
		Customer customer=customerRepo.findById(dto.getCustomerId())
		                  .orElseThrow(()-> new RuntimeException("Customer not found"));
		
		Site site=siteRepo.findById(dto.getSiteId())
				          .orElseThrow(()-> new RuntimeException("Site not found"));
		
		Technician technician=technicianRepo.findById(dto.getTechnicianId())
				          .orElseThrow(()-> new RuntimeException("Technician not found"));
		
		WorkOrder workOrder=new WorkOrder();
		workOrder.setTitle(dto.getTitle());
		workOrder.setDescription(dto.getDescription());
		workOrder.setPriority(dto.getPriority());
		workOrder.setWorkStatus(dto.getWorkStatus());
		workOrder.setCustomerId(customer);
		workOrder.setSiteId(site);
		workOrder.setTechnicianId(technician);
		workOrder.setSlaDueDate(dto.getSlaDueDate());
		
		workOrderRepo.save(workOrder);
		
		return mapDTO(workOrder);
	}



	@Override
	public WorkOrderDTO updateWorkOrder(Long id, WorkOrderDTO dto) {
		WorkOrder workOrder=workOrderRepo.findById(id)
		                   .orElseThrow(()-> new RuntimeException("Work Order not found"));
		
		Customer customer=customerRepo.findById(dto.getCustomerId())
				          .orElseThrow(()-> new RuntimeException("Customer not found"));
		
		Technician technician=technicianRepo.findById(dto.getTechnicianId())
				          .orElseThrow(()-> new RuntimeException("Technician not found"));
		
		Site site=siteRepo.findById(dto.getSiteId())
				          .orElseThrow(()-> new RuntimeException("Site not found"));
		
		WorkOrder upworkOrder=new WorkOrder();
		upworkOrder.setTitle(dto.getTitle());
		upworkOrder.setDescription(dto.getDescription());
		upworkOrder.setPriority(dto.getPriority());
		upworkOrder.setWorkStatus(dto.getWorkStatus());
		upworkOrder.setCustomerId(customer);
		upworkOrder.setSiteId(site);
		upworkOrder.setTechnicianId(technician);
		upworkOrder.setSlaDueDate(dto.getSlaDueDate());
		workOrderRepo.save(upworkOrder);
		
		return mapDTO(upworkOrder);
	}

	@Override
	public WorkOrderDTO getWorkOrder(Long id) {
		WorkOrder workOrder=workOrderRepo.findById(id)
		.orElseThrow(()-> new RuntimeException("Work Order not found"));
		return mapDTO(workOrder);
	}

	@Override
	public List<WorkOrderDTO> getAllWorkOrders() {
		
		return workOrderRepo.findAll()
		             .stream()
		             .map(this::mapDTO)
		             .toList();	
	}

	@Override
	public void deleteWorkOrder(Long id) {
		
		if(!workOrderRepo.existsById(id)) {
			throw new RuntimeException("Work Order not found");
		}
		workOrderRepo.deleteById(id);
	}

	@Override
	public List<WorkOrderDTO> getWorkOrdersByStatus(WorkStatus workSatus) {
		   return workOrderRepo.findByWorkStatus(workSatus)
	                .stream()
	                .map(this::mapDTO)
	                .toList();
		
	}

	@Override
	public List<WorkOrderDTO> getWorkOrderByTechnician(Long technicianId) {
		
		 return workOrderRepo.findByTechnicianId_Id(technicianId)
	                .stream()
	                .map(this::mapDTO)
	                .toList();
	
	}

	@Override
	public List<WorkOrderDTO> getWorkOrderByCustomer(Long customerId) {
		
		return workOrderRepo.findByCustomerId_Id(customerId)
				     .stream()
				     .map(this::mapDTO)
				     .toList();
	}
}