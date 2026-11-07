package com.keystone.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keystone.dto.SiteDTO;

@Service
public interface SiteService {

	public SiteDTO createSite(SiteDTO site);
	public SiteDTO updateSite(Long siteId,SiteDTO sites);
	public SiteDTO getSite(Long id);
	public List<SiteDTO> getAllSite(Long customerId);
	public void deleteSite(Long id);
}
