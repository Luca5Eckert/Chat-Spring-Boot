package com.projetospring.chatonline.modules.user.aplication.repository;

import java.util.Optional;
import java.util.UUID;

import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

	Optional<UserEntity> findByUsername(String username);
	
	Optional<UserEntity> findByEmail(String email);

	
}
