package com.keystone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keystone.Service.SiteService;
import com.keystone.dto.SiteDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sites")
@RequiredArgsConstructor
public class SiteController {

	@Autowired
	SiteService siteService;
	
	@PostMapping("/create")
	public ResponseEntity<SiteDTO> createSite(@RequestBody SiteDTO site){
		return ResponseEntity.ok(siteService.createSite(site));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<SiteDTO> updateSite(@PathVariable Long id, @RequestBody SiteDTO dtos){
		return ResponseEntity.ok(siteService.updateSite(id,dtos));
	}
	
	@GetMapping("/getSite/{id}")
	public ResponseEntity<SiteDTO> getSite(@PathVariable Long id){
		return ResponseEntity.ok(siteService.getSite(id));
	}
	
	@GetMapping("/all/{customerId}")
	public ResponseEntity<List<SiteDTO>> getAllSite(@PathVariable Long customerId){
		return ResponseEntity.ok(siteService.getAllSite(customerId));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteSite(@PathVariable Long id){
			siteService.deleteSite(id);
			return ResponseEntity.ok("Site deleted successfully");
	}
}
