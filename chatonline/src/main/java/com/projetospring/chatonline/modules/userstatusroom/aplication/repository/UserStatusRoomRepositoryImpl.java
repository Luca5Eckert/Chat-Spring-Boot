package com.projetospring.chatonline.modules.userstatusroom.aplication.repository;

import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoom;
import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoomId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserStatusRoomRepositoryImpl implements UserStatusRoomRepository {

    private final JpaUserStatusRoomRepository jpaUserStatusRoomRepository;

    public UserStatusRoomRepositoryImpl(JpaUserStatusRoomRepository jpaUserStatusRoomRepository) {
        this.jpaUserStatusRoomRepository = jpaUserStatusRoomRepository;
    }


    @Override
    public Optional<UserStatusRoom> findById(UserStatusRoomId idUserStatusRoom) {
        return jpaUserStatusRoomRepository.findById(idUserStatusRoom);
    }

    @Override
    public List<UserStatusRoom> findAll() {
        return jpaUserStatusRoomRepository.findAll();
    }

    @Override
    public UserStatusRoom save(UserStatusRoom userStatusRoom) {
        return jpaUserStatusRoomRepository.save(userStatusRoom);
    }

    @Override
    public void deleteById(UserStatusRoomId idUserStatusRoom) {
        jpaUserStatusRoomRepository.deleteById(idUserStatusRoom);
    }

    @Override
    public boolean existsById(UserStatusRoomId idUserStatusRoom) {
        return jpaUserStatusRoomRepository.existsById(idUserStatusRoom);
    }
}
