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

import com.keystone.Entity.Technician;
import com.keystone.Service.TechnicianServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/technician")
@RequiredArgsConstructor
public class TechnicianController {

	private final TechnicianServiceImpl technicianService;
	
	@PostMapping("/post")
	public ResponseEntity<Technician> createTechnician(@RequestBody Technician technician){
		Technician created=technicianService.createTechnician(technician);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Technician> getTechnician(@PathVariable Long id){
		return ResponseEntity.ok(technicianService.getTechnician(id));
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Technician>> getAllTechnician(){
		return ResponseEntity.ok(technicianService.getAllTechnician());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Technician> updateTechnician(@PathVariable Long id, @RequestBody Technician technician){
		return ResponseEntity.ok(technicianService.updateTechnician(id, technician));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTechnician(@PathVariable Long id){
		technicianService.deleteTechnician(id);
		return ResponseEntity.ok("Technician deleted successfully");
	}
	
}
