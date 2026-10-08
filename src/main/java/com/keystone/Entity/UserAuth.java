package com.keystone.Entity;

import com.keystone.Enum.Role;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="user_auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuth {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="first_name",nullable=false)
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(nullable=false, unique=true)
	private String userEmail;
	
	private String phone;
	
	@Column(nullable=false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	
}
