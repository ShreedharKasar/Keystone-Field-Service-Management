package com.keystone.Service;

import java.util.List;

import com.keystone.Entity.Technician;

public interface TechnicianService {

	public Technician createTechnician(Technician technician);
	public Technician getTechnician(Long id);
	public List<Technician> getAllTechnician();
	public Technician updateTechnician(Long id, Technician technician);
	public void deleteTechnician(Long id);
}
