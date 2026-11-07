package com.keystone.Service;

import java.util.List;

import com.keystone.dto.CustomerDTO;

public interface CustomerService {

	public CustomerDTO createCustomer(CustomerDTO cus);
	
	public CustomerDTO updateCustomer(Long id,CustomerDTO cust);
	
	public CustomerDTO getCustomer(Long id);
	
	public List<CustomerDTO> getAllCustomers();
	
	public void deleteCustomers(Long id);
}
