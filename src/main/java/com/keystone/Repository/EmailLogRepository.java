package com.keystone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keystone.Entity.EmailLogs;

public interface EmailLogRepository extends JpaRepository<EmailLogs, Integer> {

}
