package com.projetospring.chatonline.modules.userstatusroom.domain.services;

import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import com.projetospring.chatonline.modules.userstatusroom.aplication.exceptions.UserStatusRoomNotFoundException;
import com.projetospring.chatonline.modules.userstatusroom.aplication.repository.UserStatusRoomRepository;
import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoom;
import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoomId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserStatusRoomService {

    @Autowired
    private UserStatusRoomRepository userStatusRoomRepository;

    public UserStatusRoom getRelationshipUserWithRoom(UserEntity user, Room room){
        UserStatusRoomId userStatusRoomId = new UserStatusRoomId(user,room);
        return userStatusRoomRepository.findById(userStatusRoomId).orElseThrow(() -> new UserStatusRoomNotFoundException("User status room not found"));
    }

}
