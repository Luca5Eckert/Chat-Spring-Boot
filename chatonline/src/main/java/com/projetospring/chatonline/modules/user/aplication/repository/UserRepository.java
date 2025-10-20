package com.projetospring.chatonline.modules.user.aplication.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository {

	Optional<UserEntity> findById(UUID idUser);

	List<UserEntity> findAll();

	UserEntity save(UserEntity user);

	void deleteById(UUID idUser);

	boolean existsById(UUID idUser);

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByUsername(String username);


}
