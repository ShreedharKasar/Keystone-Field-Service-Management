package com.keystone.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keystone.Entity.Technician;
import com.keystone.Enum.AvailableStatus;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {

	Optional<Technician> findByEmpCode(String empCode);
	Optional<Technician> findByEmail(String email);
	List<Technician> findByActiveTrue();
	List<Technician> findByAvailable(AvailableStatus available);
	boolean existsByEmpCode(String empCode);
	boolean existsByEmail(String email);
}
