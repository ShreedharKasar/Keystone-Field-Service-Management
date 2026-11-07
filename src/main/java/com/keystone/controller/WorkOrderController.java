package com.keystone.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keystone.Enum.WorkStatus;
import com.keystone.Service.WorkOrderService;
import com.keystone.dto.WorkOrderDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workOrder")
@RequiredArgsConstructor
public class WorkOrderController {

	private final WorkOrderService workOrderService;
	
	@PostMapping("/create")
	public ResponseEntity<WorkOrderDTO> createWorkOrder(@RequestBody WorkOrderDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED)
				             .body(workOrderService.createWorkOrder(dto));
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<WorkOrderDTO> getWorkOrder(@PathVariable Long id){
		return ResponseEntity.ok(workOrderService.getWorkOrder(id));
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<WorkOrderDTO>> getAllworkOrder(){
		return ResponseEntity.ok(workOrderService.getAllWorkOrders());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<WorkOrderDTO> updateWorkOrder(@PathVariable Long id,  @RequestBody WorkOrderDTO dto){
		return ResponseEntity.ok(workOrderService.updateWorkOrder(id, dto));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteWorkOrder(@PathVariable Long id) {
		workOrderService.deleteWorkOrder(id);
		return ResponseEntity.ok("Work Order deleted Successfully");
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<WorkOrderDTO>> getWorkOrdersByStatus(@PathVariable WorkStatus workStatus){
		return ResponseEntity.ok(workOrderService.getWorkOrdersByStatus(workStatus));
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<WorkOrderDTO>> getWorkOrdersByCustomer(@PathVariable Long customerId){
		return ResponseEntity.ok(workOrderService.getWorkOrderByCustomer(customerId));
	}
	
	@GetMapping("/technician/{technicianId}")
	public ResponseEntity<List<WorkOrderDTO>> getWorkOrdersByTechnician(@PathVariable Long technicianId){
		return ResponseEntity.ok(workOrderService.getWorkOrderByTechnician(technicianId));
	}
}
