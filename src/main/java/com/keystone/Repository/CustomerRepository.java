package com.keystone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keystone.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer>findByUserEmail(String userEmail);
	boolean existsByUserEmail(String userEmail);
	Optional<Customer>findByPhone(String phone);
	boolean existsByPhone(String phone);

 
}
