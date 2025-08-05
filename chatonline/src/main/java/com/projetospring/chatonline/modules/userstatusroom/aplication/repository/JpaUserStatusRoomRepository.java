package com.projetospring.chatonline.modules.userstatusroom.aplication.repository;

import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoom;
import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserStatusRoomRepository extends JpaRepository<UserStatusRoom, UserStatusRoomId> {

}