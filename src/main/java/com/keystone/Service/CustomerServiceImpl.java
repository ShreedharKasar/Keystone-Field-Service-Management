	package com.keystone.Service;
	
	import java.time.LocalDateTime;
	import java.util.List;
	import java.util.stream.Collectors;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	
	import com.keystone.Entity.Customer;
	import com.keystone.Repository.CustomerRepository;
	import com.keystone.dto.CustomerDTO;
	
	@Service
	public class CustomerServiceImpl implements CustomerService{
	
		@Autowired
		CustomerRepository customerRepo;
		
		@Override
		public CustomerDTO createCustomer(CustomerDTO cus) {
			
			if(customerRepo.existsByUserEmail(cus.customerEmail)) {
				throw new RuntimeException("Customer Already exist");
			}
			
			if(customerRepo.existsByPhone(cus.customerPhone)) {
				throw new RuntimeException("Customer already exist");
			}
			
			Customer customer=new Customer();
			customer.setUserEmail(cus.getCustomerEmail());
			customer.setContactPerson(cus.getContactPerson());
			customer.setPhone(cus.getCustomerPhone());
			customer.setAddress(cus.getAddress());
			customer.setActive(true);
			customer.setCreatedAt(LocalDateTime.now());
			
			Customer savedCustomer=customerRepo.save(customer);
			return mapDTO(savedCustomer);
		}
	
		@Override
	    public CustomerDTO updateCustomer(Long id,CustomerDTO cust) {
			
			Customer customer = customerRepo.findById(id)
					     .orElseThrow(()-> new RuntimeException("Customer not found"));
			
			customer.setContactPerson(cust.contactPerson);
			customer.setPhone(cust.customerPhone);
			customer.setAddress(cust.address);
			customerRepo.save(customer);
			return mapDTO(customer);	
		}
	    
		@Override
	    public CustomerDTO getCustomer(Long id) {
	    	
	    	Customer customer=customerRepo.findById(id)
	    			                      .orElseThrow(() -> new RuntimeException("Customer not found"));
	    	return mapDTO(customer);
	    }
	    
		@Override
	    public List<CustomerDTO> getAllCustomers(){
	    	return customerRepo.findAll().stream().map(this::mapDTO).collect(Collectors.toList());
	    }
	    
		@Override
	    public void deleteCustomers(Long id) {
	    	customerRepo.deleteById(id);
	    }
	    
	    public CustomerDTO mapDTO(Customer customers) {
	    	
	    	CustomerDTO dto=new CustomerDTO();
	    	 dto.setId(customers.getId());
	    	dto.setCustomerEmail(customers.getUserEmail());
	    	dto.setCustomerPhone(customers.getPhone());
	    	dto.setAddress(customers.getAddress());
	    	dto.setContactPerson(customers.getContactPerson());
	    	dto.setActive(customers.isActive());
	    		
	    	return dto;
	    }
	
	}
