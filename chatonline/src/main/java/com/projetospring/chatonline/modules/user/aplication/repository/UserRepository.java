package com.projetospring.chatonline.modules.user.aplication.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetospring.chatonline.modules.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);

	
}
