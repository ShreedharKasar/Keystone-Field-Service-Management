package com.keystone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.util.List;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.keystone.security.CustomUserDetailService;
import com.keystone.security.JWTFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomUserDetailService userDetailsService;
	private final JWTFilter jwtFilter;
	
	
	//PasswordEncoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

	    CorsConfiguration configuration = new CorsConfiguration();

	    
	    configuration.setAllowedOrigins(
	        List.of("http://localhost:5173")
	    );

	    
	    configuration.setAllowedMethods(
	        List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
	    );

	    
	    configuration.setAllowedHeaders(
	        List.of("Authorization", "Content-Type")
	    );

	    
	    configuration.setAllowCredentials(false);

	    UrlBasedCorsConfigurationSource source =
	        new UrlBasedCorsConfigurationSource();

	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}
	
	//Authentication Provider
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userDetailsService);
		
//		provider.setUserDetailsPasswordService((UserDetailsPasswordService) userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
	
	//Authentication Manager
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
	}
	
	//Security Filter Chain
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		http
		
		.cors(Customizer.withDefaults())
		.csrf(csrf -> csrf.disable())
		
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		
		.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/register",
				                                            "/api/auth/login",
				                                             "/api/auth/forgot-password",
				                                            "/api/auth/reset-password",
				                                            "/api/customer/test",
				                                            "/api/email_log/resetPasswordEmail"
				                                            )
				.permitAll()
				.anyRequest().authenticated()
				)
		
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		return http.build();
	}
	
}
