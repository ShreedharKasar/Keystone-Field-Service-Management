package com.keystone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keystone.Entity.UserAuth;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {

	Optional<UserAuth> findByUserEmail(String userEmail);  //optional is used to avoid NullPointerException
	
	boolean existsByUserEmail(String userEmail);
}
