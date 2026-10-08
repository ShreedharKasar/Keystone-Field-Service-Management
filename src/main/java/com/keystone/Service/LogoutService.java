package com.keystone.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keystone.Entity.RevokedToken;
import com.keystone.Repository.RevokedTokenRepository;
import com.keystone.security.JWTService;

@Service
public class LogoutService {

    @Autowired
    private RevokedTokenRepository revokedTokenRepository;

    @Autowired
    private JWTService jwtService;

    public void logout(String token) {

        // Check if token is already revoked
        if (revokedTokenRepository.findByToken(token).isPresent()) {
            return;
        }

        // Get token expiry date
        LocalDateTime expiryDate = jwtService.extractExpiration(token);

        // Create revoked token object
        RevokedToken revokedToken = new RevokedToken();

        revokedToken.setToken(token);
        revokedToken.setExpiryDate(expiryDate);

        // Save token
        revokedTokenRepository.save(revokedToken);
    }
}
