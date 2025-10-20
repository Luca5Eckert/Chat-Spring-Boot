package com.projetospring.chatonline.modules.user.aplication.repository;


import com.projetospring.chatonline.modules.user.domain.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository{

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<UserEntity> findById(UUID idUser) {
        return jpaUserRepository.findById(idUser);
    }

    @Override
    public List<UserEntity> findAll() {
        return jpaUserRepository.findAll();
    }

    @Override
    public UserEntity save(UserEntity user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public void deleteById(UUID idUser) {
        jpaUserRepository.deleteById(idUser);
    }

    @Override
    public boolean existsById(UUID idUser) {
        return jpaUserRepository.existsById(idUser);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return Optional.ofNullable(jpaUserRepository.findByEmail(email));
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return Optional.ofNullable(jpaUserRepository.findByUsername(username));
    }
}
