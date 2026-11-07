package com.keystone.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keystone.Entity.Technician;
import com.keystone.Repository.TechnicianRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TechnicianServiceImpl implements TechnicianService {

	@Autowired
	TechnicianRepository technicianRepo; 
	
	public Technician createTechnician(Technician technician) {
		
		if(technicianRepo.existsByEmpCode(technician.getEmpCode())) {
			throw new RuntimeException("Emp code already exist");
		}
		
		if(technicianRepo.existsByEmail(technician.getEmail())) {
			throw new RuntimeException("Emp emadil already exist");
		}
		
		technician.setId(null);
		technician.setActive(true);
		
		return technicianRepo.save(technician);
	}
	
	@Transactional(readOnly=true)
	public Technician getTechnician(Long id) {
		return technicianRepo.findById(id)
				             .orElseThrow(() -> new RuntimeException("Technician not found"+ id));
	}
	
	@Transactional(readOnly=true)
	public List<Technician> getAllTechnician(){
		return technicianRepo.findAll();
	}
	
	public Technician updateTechnician(Long id, Technician technicians) {
		Technician existing=getTechnician(id);
		
//		if(existing.getEmail().equalsIgnoreCase(technicians.getEmail() && technicianRepo.existbyEmail(technicians.getEmail()))) {
//			throw new RuntimeException("Email already exist");
//		}
		
		existing.setName(technicians.getName());
		existing.setEmail(technicians.getEmail());
		existing.setPhone(technicians.getPhone());
		existing.setSpecialization(technicians.getSpecialization());
		existing.setAvailable(technicians.getAvailable());
		existing.setCity(technicians.getCity());
		existing.setState(technicians.getState());
		existing.setZipCode(technicians.getZipCode());
		
		return technicianRepo.save(existing);
		
	}
	
	public void deleteTechnician(Long id) {
		Technician technician=getTechnician(id);
		technician.setActive(false);
		technicianRepo.save(technician);
	}
}
