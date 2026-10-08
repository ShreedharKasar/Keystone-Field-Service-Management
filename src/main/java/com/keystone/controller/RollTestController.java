package com.keystone.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RollTestController {

	@GetMapping("/api/customer/test")
	@PreAuthorize("hasRole('CUSTOMER')")
	public String customerTest() {
		return "Customer Api accessed Successfully";
	}
	
	@GetMapping("/api/technician/test")
	@PreAuthorize("hasRole('TECHNICIAN')")
	public String technicianTest() {
		return "Technician Api accessed Successfully";
	}
	
	@GetMapping("/api/admin/test")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminTest() {
		return "Admin Api accessed Successfully";
	}
	
	@GetMapping("/api/manager/test")
	@PreAuthorize("hasRole('MANAGER')")
	public String managerTest() {
		return "Manager accessed Successfully";
	}
	
	@GetMapping("/api/dispatcher/test")
	@PreAuthorize("hasRole('DISPATCHER')")
	public String dispatcherTest() {
		return "Dispatcher api accessed Successfully";
	}
}
