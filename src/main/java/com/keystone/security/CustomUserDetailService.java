package com.keystone.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.keystone.Entity.UserAuth;
import com.keystone.Repository.UserAuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

	private final UserAuthRepository userAuthRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserAuth user=userAuthRepo.findByUserEmail(username).orElseThrow(() -> 
		new UsernameNotFoundException("User not found with email: "+username));
		
		return User.builder()
			   .username(user.getUserEmail())
			   .password(user.getPassword())
			   .roles(user.getRole().name())
			   .build();
	}

}
