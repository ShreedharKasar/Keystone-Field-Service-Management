package com.keystone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keystone.Entity.UserAuth;
import com.keystone.Service.LogoutService;
import com.keystone.Service.UserAuthService;
import com.keystone.dto.ChangePassword;
import com.keystone.dto.ForgotPassword;
import com.keystone.dto.LoginRequest;
import com.keystone.dto.LoginResponse;
import com.keystone.dto.RegisterRequest;
import com.keystone.dto.ResetPassword;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthController {

	private final UserAuthService userAuthService;
	
	@Autowired
	private LogoutService logoutService;
	
	@PostMapping("/register")
	public ResponseEntity<UserAuth> rigister(@RequestBody RegisterRequest request){
		
		UserAuth user= userAuthService.registerUser(request);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
		
		LoginResponse response=userAuthService.loginUser(request);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestBody ForgotPassword request){
		
		String token=userAuthService.forgotPassword(request);
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody ResetPassword request){
		
		 request.setToken(token);
		 
		String msg=userAuthService.resetPassword(request);
		return ResponseEntity.ok(msg);
	}
	
	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody ChangePassword request, Authentication authentication){
		
		String email=authentication.getName();
		
		userAuthService.changePassword(
				email, 
				request.getOldPassword(),
				request.getNewPassword()
				);
		
		return ResponseEntity.ok("Password changed successfully");
		
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(
	        @RequestHeader("Authorization") String authHeader) {

	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        return ResponseEntity.badRequest()
	                .body("Invalid Authorization header");
	    }

	    String token = authHeader.substring(7);

	    logoutService.logout(token);

	    return ResponseEntity.ok("Logout successful");
	}
}
