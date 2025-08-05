package com.projetospring.chatonline.modules.user.aplication.repository;

import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
