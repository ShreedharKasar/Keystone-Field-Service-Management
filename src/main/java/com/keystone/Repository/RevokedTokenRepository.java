package com.keystone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keystone.Entity.RevokedToken;

public interface RevokedTokenRepository extends JpaRepository<RevokedToken, Integer> {

	Optional<RevokedToken> findByToken(String token);
}
