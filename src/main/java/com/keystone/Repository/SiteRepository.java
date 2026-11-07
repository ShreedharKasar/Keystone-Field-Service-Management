package com.keystone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keystone.Entity.Site;

public interface SiteRepository extends JpaRepository<Site, Long>{

	List<Site> findByCustomerId(Long customerId);
}
