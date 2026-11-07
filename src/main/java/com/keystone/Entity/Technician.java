package com.keystone.Entity;

import java.time.LocalDateTime;

import com.keystone.Enum.AvailableStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="technician")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Technician {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private String phone;
	private String specialization;
	private String empCode;
    @Enumerated(EnumType.STRING)
	private AvailableStatus available;
    private boolean active=true;
    private LocalDateTime createdAt;
    private String city;
    private String state;
    private String zipCode;
    
    @PrePersist
    public void onCreate() {
    	if(createdAt==null) {
    		createdAt=LocalDateTime.now();
    	}
    }
}
