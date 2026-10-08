package com.keystone.dto;
import com.keystone.Enum.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

	private String firstName;
	private String lastName;
	private String userEmail;
	private String phone;
	private String password;
	private Role role;
	

	
}
