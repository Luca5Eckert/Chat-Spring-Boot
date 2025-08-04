package com.projetospring.chatonline.modules.room.domain.cases;

import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import com.projetospring.chatonline.modules.userstatusroom.domain.enums.PermissionType;
import com.projetospring.chatonline.modules.userstatusroom.domain.validator.PermissionValidatorService;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.modules.room.aplication.dtos.RoomDto;
import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepository;

@Service
public class DeleteRoomCase {
    
    private final RoomRepository repository;

    private final PermissionValidatorService permissionValidatorService;

    public DeleteRoomCase(RoomRepository roomRepository, PermissionValidatorService permissionValidatorService){
        this.repository = roomRepository;
        this.permissionValidatorService = permissionValidatorService;
    }



    public void execute(RoomDto roomDto, UserEntity userEntity){
        Room room = repository.findById(roomDto.id()).orElseThrow();

        permissionValidatorService.checkPermission(userEntity, room, PermissionType.DELETE_ROOM);
    }



}
