package com.projetospring.chatonline.modules.userstatusroom.aplication.repository;

import com.projetospring.chatonline.modules.room.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoom;
import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoomId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserStatusRoomRepository {

    Optional<UserStatusRoom> findById(UserStatusRoomId idUserStatusRoom);

    List<UserStatusRoom> findAll();

    UserStatusRoom save(UserStatusRoom userStatusRoom);

    void deleteById(UserStatusRoomId idUserStatusRoom);

    boolean existsById(UserStatusRoomId idUserStatusRoom);

}
