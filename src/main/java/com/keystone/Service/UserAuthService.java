package com.keystone.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.keystone.Entity.PasswordResetToken;
import com.keystone.Entity.UserAuth;
import com.keystone.Repository.PasswordRestTokenRepo;
import com.keystone.Repository.UserAuthRepository;
import com.keystone.dto.ForgotPassword;
import com.keystone.dto.LoginRequest;
import com.keystone.dto.LoginResponse;
import com.keystone.dto.RegisterRequest;
import com.keystone.dto.ResetPassword;
import com.keystone.security.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthService {
	
	private final UserAuthRepository userAuthRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JWTService jwtService;
	private final PasswordRestTokenRepo passwordResetRepo;
	private final EmailService emailService;
	
	public UserAuth registerUser(RegisterRequest request) {
		//checking email is exists or not
		if(userAuthRepository.existsByUserEmail(request.getUserEmail())) {
			throw new RuntimeException("Email already Registerd"); 
		}
		
		//else create new Id
		UserAuth user=new UserAuth();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setUserEmail(request.getUserEmail());
		user.setPhone(request.getPhone());
		
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		user.setRole(request.getRole());
		
		return userAuthRepository.save(user);
	}

	public LoginResponse loginUser(LoginRequest request) {
		
		//Authenticate userName + Password
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getUserEmail(),
				request.getPassword()
				)
			);
		
		
		//Get user from database
		UserAuth user=userAuthRepository.findByUserEmail(request.getUserEmail())
				      .orElseThrow(() -> 
				      new RuntimeException("User not Found"));
		
		
		
		//Generate JWT
		String token=jwtService.generateToken(
				     user.getUserEmail(),
				     user.getRole().name()
				   );
		
		
		//return response
		return LoginResponse.builder()
				.message("Login Successfull")
				.userEmail(user.getUserEmail())
				.role(user.getRole().name())
				.token(token)
				.build();
		
	}
	
	
	public String forgotPassword(ForgotPassword request) {
		
		UserAuth user=userAuthRepository.findByUserEmail(request.getUserEmail()).orElseThrow(() ->
		       new RuntimeException("User not found"));
		
		//delete oldToken if exist
		passwordResetRepo.deleteByUser(user);
		
		//Generate new Token
		String token=UUID.randomUUID().toString();
		
		//resetPassword
		PasswordResetToken resetToken=new PasswordResetToken();
		
		resetToken.setToken(token);
		resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(15));
		resetToken.setUsed(false);
		
		//Connect token with the user
	    resetToken.setUser(user);	
	   
	    //create reset link
		passwordResetRepo.save(resetToken);
		
		String resetLink =
		        "http://localhost:8080/api/auth/reset-password?token="
		        + token;

		//send email
		emailService.sentResetPasswordLink(
		        user.getUserEmail(),
		        resetLink
		);
		
		return token;
	}
	
	
	public String resetPassword(ResetPassword request) {
		System.out.println("TOKEN = " + request.getToken());
	    System.out.println("NEW PASSWORD = " + request.getNewPassword());
		
		//Find Token
		PasswordResetToken resetToken=passwordResetRepo.findByToken(request.getToken()).orElseThrow(() ->
		        new RuntimeException("Invail Token"));
		
		
		//Check whether token is already used
		if(resetToken.isUsed()) { 
			throw new RuntimeException("Token already used");
		}
		
		
		//check token expire
		if(resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("Token Expired");
		}
		
		
		//get user associated with token
		UserAuth user=resetToken.getUser();
		
		 System.out.println("USER EMAIL = " + user.getUserEmail());
		
		//Encrypt the new password
		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		
		userAuthRepository.save(user);
		
		resetToken.setUsed(true);
		passwordResetRepo.save(resetToken);
		
		return "Password Reset Successfully";
	}
	
	
	public void changePassword(String userEmail, String oldPassword, String newPassword) {
		
		UserAuth user=userAuthRepository.findByUserEmail(userEmail).orElseThrow(() ->
		         new RuntimeException("User not found"));
		
		if(!passwordEncoder.matches(oldPassword, user.getPassword())) {
			throw new RuntimeException("OldPassword is incorrect!!!");
		}
		
		user.setPassword(passwordEncoder.encode(newPassword));
		
		userAuthRepository.save(user);
	}
	

}
