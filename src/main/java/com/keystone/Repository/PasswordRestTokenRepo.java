package com.keystone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.keystone.Entity.PasswordResetToken;
import com.keystone.Entity.UserAuth;

public interface PasswordRestTokenRepo extends JpaRepository<PasswordResetToken, Integer>{

	Optional<PasswordResetToken> findByToken(String token);
	
	Optional<PasswordResetToken> findByUser(UserAuth user);
	
    @Transactional
	void deleteByUser(UserAuth user);
}
