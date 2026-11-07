package com.keystone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keystone.Service.CustomerService;
import com.keystone.dto.CustomerDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/create")
	public ResponseEntity<CustomerDTO>createCustomer(@RequestBody CustomerDTO cus){
		return ResponseEntity.ok(customerService.createCustomer(cus));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CustomerDTO>updateCustomer(@PathVariable Long id,@RequestBody CustomerDTO Cust){
		return ResponseEntity.ok(customerService.updateCustomer(id, Cust));
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<CustomerDTO>getCustomer(@PathVariable Long id){
		return ResponseEntity.ok(customerService.getCustomer(id));
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CustomerDTO>>getAllCustomer(){
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteCustomer(@PathVariable Long id){
		customerService.deleteCustomers(id);
		return ResponseEntity.ok("Customer deleted successfully");
	}
	
}
