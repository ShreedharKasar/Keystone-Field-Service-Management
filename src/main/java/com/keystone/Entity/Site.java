package com.keystone.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="site_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Site {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String siteName;
	private String buildingName; 	
	private Long buildingNo;
	private Long lanNo;
	private Long floorNo;
	private Long roomNo;
	private String cityName;
	private String stateName;
	private String landMark;
	private String countryName;
	private Long zipCode;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private Customer customer;
}
