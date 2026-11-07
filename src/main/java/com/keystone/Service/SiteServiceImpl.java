package com.keystone.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keystone.Entity.Customer;
import com.keystone.Entity.Site;
import com.keystone.Repository.CustomerRepository;
import com.keystone.Repository.SiteRepository;
import com.keystone.dto.SiteDTO;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	SiteRepository siteRepo;
	@Autowired
	CustomerRepository customerRepo;
	
	@Override
	public SiteDTO createSite(SiteDTO site) {
		Customer customer=customerRepo.findById(site.getCustomerId())
		             .orElseThrow(() -> new RuntimeException("Customer not found"));
		
		Site sites=new Site();
		sites.setSiteName(site.getSiteName());
		sites.setBuildingName(site.getBuildingName());
		sites.setBuildingNo(site.getBuildingNo());
		sites.setRoomNo(site.getRoomNo());
		sites.setFloorNo(site.getFloorNo());
		sites.setLanNo(site.getLanNo());
		sites.setCityName(site.getCityName());
		sites.setStateName(site.getStateName());
		sites.setCountryName(site.getCountryName());
		sites.setZipCode(site.getZipCode());
		sites.setLandMark(site.getLandMark());
		sites.setCustomer(customer);
		siteRepo.save(sites);
		
		return mapToDTO(sites);
		
	}
	@Override
	public SiteDTO updateSite(Long id, SiteDTO dtos) {
		Site site= siteRepo.findById(id).orElseThrow(()-> new RuntimeException("Site not found"));
		site.setSiteName(dtos.getSiteName());
		site.setCityName(dtos.getCityName());
		site.setStateName(dtos.getStateName());
		site.setCountryName(dtos.getCountryName());
		site.setZipCode(dtos.getZipCode());
		siteRepo.save(site);
		return mapToDTO(site);
		
		
	}
	@Override
	public SiteDTO getSite(Long id) {
		Site site= siteRepo.findById(id)
				 .orElseThrow(()-> new RuntimeException("Site not found"));
		
		return mapToDTO(site);
	}
	@Override
	public List<SiteDTO> getAllSite(Long customerId) {
		return siteRepo.findByCustomerId(customerId)
				.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}
	@Override
	public void deleteSite(Long id) {
		siteRepo.deleteById(id);
		
	}
	
    private SiteDTO mapToDTO(Site site) {
		
		SiteDTO dto= new SiteDTO();
		dto.setId(site.getId());
		dto.setSiteName(site.getSiteName());
	    dto.setLanNo(site.getLanNo());
		dto.setBuildingName(site.getBuildingName());
		dto.setBuildingNo(site.getBuildingNo());
		dto.setFloorNo(site.getFloorNo());
		dto.setRoomNo(site.getRoomNo());
		dto.setLandMark(site.getLandMark());
		dto.setCityName(site.getCityName());
		dto.setStateName(site.getStateName());
		dto.setCountryName(site.getCountryName());
		dto.setZipCode(site.getZipCode());
		dto.setCustomerId(site.getCustomer().getId());
		
		return dto;
	}
	
	
}
