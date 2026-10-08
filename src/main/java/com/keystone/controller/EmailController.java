package com.keystone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keystone.Service.EmailService;
import com.keystone.dto.EmailLogsDTO;

@RestController
@RequestMapping("/api/email_log")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/resetPasswordEmail")
	public ResponseEntity<String>sendResetPasswordEmail(@RequestParam String to,@RequestParam String token ){
		String result= emailService.sentResetPasswordLink(to, token);
		return ResponseEntity.ok(result);
	
	}
	
	@PostMapping("/notify")
	public ResponseEntity<String>notification(@RequestBody EmailLogsDTO emailLog){
		String result= emailService.notify(emailLog);
		return ResponseEntity.ok(result);
	}
	
}
