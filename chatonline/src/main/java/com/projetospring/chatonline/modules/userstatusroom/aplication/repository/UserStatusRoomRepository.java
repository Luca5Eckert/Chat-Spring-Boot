package com.projetospring.chatonline.modules.userstatusroom.aplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoom;
import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoomId;

public interface UserStatusRoomRepository extends JpaRepository<UserStatusRoom, UserStatusRoomId> {

}
