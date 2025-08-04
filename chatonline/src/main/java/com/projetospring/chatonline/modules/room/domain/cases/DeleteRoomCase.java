package com.projetospring.chatonline.modules.room.domain.cases;

import com.projetospring.chatonline.core.cases.UseCase;
import com.projetospring.chatonline.modules.room.aplication.dtos.DeleteRoomCommand;
import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.userstatusroom.domain.enums.PermissionType;
import com.projetospring.chatonline.modules.userstatusroom.domain.validator.PermissionValidatorService;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepository;

@Service
public class DeleteRoomCase implements UseCase<DeleteRoomCommand, Void> {
    
    private final RoomRepository repository;

    private final PermissionValidatorService permissionValidatorService;

    public DeleteRoomCase(RoomRepository roomRepository, PermissionValidatorService permissionValidatorService){
        this.repository = roomRepository;
        this.permissionValidatorService = permissionValidatorService;
    }



    public Void execute(DeleteRoomCommand deleteRoomCommand){
        Room room = repository.findById(deleteRoomCommand.roomDto().id()).orElseThrow();

        permissionValidatorService.checkPermission(deleteRoomCommand.userEntity(), room, PermissionType.DELETE_ROOM);


        return null;

    }



}
