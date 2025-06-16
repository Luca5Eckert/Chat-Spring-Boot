package com.projetospring.chatonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetospring.chatonline.model.UserStatusRoom;
import com.projetospring.chatonline.model.UserStatusRoomId;

public interface UserStatusRoomRepository extends JpaRepository<UserStatusRoom, UserStatusRoomId> {

}
