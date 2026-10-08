package com.keystone.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.keystone.Entity.EmailLogs;
import com.keystone.Repository.EmailLogRepository;
import com.keystone.dto.EmailLogsDTO;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMail;
	@Autowired
	private EmailLogRepository emailLogsRepo;
	
	
	public String sentResetPasswordLink(String toEmail, String token) {

	    try {

	        MimeMessage message = javaMail.createMimeMessage();

	        MimeMessageHelper helper =
	                new MimeMessageHelper(message, true);

	        helper.setTo(toEmail);
	        helper.setFrom("shreedharkasar7@gmail.com");
	        helper.setSubject("Reset Your Password");

	        String resetLink =
	                "http://localhost:8080/api/auth/reset-password?token="
	                + token;

	        String body =
	                "Click the below link to reset your password:\n\n"
	                + resetLink
	                + "\n\nThis link will expire in 10 minutes.";

	        helper.setText(body, false);

	        javaMail.send(message);

	        System.out.println("Password reset email sent successfully");
	        
	        return "Reset password email sent successfully";

	    } catch (Exception e) {

	        System.out.println("Email sending failed");
	        e.printStackTrace();

	        throw new RuntimeException("Failed to send reset password email", e);
	    }
	}
	
	public String notify(EmailLogsDTO email) {
		
		boolean sentStatus=false;
		
		try {
			MimeMessage message= javaMail.createMimeMessage();
			MimeMessageHelper helper= new MimeMessageHelper(message,true);
			
			helper.setTo(email.recipientEmail);
			helper.setSubject(email.subject);
			helper.setText(email.body,true);
			
			javaMail.send(message);
			sentStatus=true;
			
		} catch (Exception e) {
			sentStatus=false;
		}
		
		EmailLogs logs = new EmailLogs(
			    email.recipientEmail,
			    email.subject,
			    email.body,
			    sentStatus
			);
		emailLogsRepo.save(logs);
		
		return sentStatus ? "Email sent Successfully":"Email sending failed";
	}


	
}
