package com.keystone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SiteDTO {

	private Long id;

	private String siteName;
	private Long lanNo;
	private String buildingName;
	private Long buildingNo;
	private Long floorNo;
	private Long roomNo;
	private String cityName;
	private String stateName;
	private String landMark;
	private String countryName;
	private Long zipCode;
	private Long customerId;
}
