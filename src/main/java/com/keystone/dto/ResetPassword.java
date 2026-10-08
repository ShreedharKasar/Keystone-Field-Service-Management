package com.keystone.dto;

import lombok.Data;

@Data
public class ResetPassword {
	
	private String token;
	private String newPassword;

}
