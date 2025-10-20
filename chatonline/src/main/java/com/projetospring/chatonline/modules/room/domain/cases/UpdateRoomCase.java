package com.projetospring.chatonline.modules.room.domain.cases;

import com.projetospring.chatonline.core.cases.UseCase;
import com.projetospring.chatonline.modules.room.aplication.dtos.UpdateRoomCommand;
import com.projetospring.chatonline.modules.room.aplication.repository.RoomRepository;
import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.userstatusroom.domain.enums.PermissionType;
import com.projetospring.chatonline.modules.userstatusroom.domain.validator.PermissionValidatorService;
import org.springframework.stereotype.Service;

@Service
public class UpdateRoomCase implements UseCase<UpdateRoomCommand, Void> {

    private final RoomRepository repository;
    private final PermissionValidatorService permissionValidatorService;

    public UpdateRoomCase(RoomRepository repository, PermissionValidatorService permissionValidatorService) {
        this.repository = repository;
        this.permissionValidatorService = permissionValidatorService;
    }

    @Override
    public Void execute(UpdateRoomCommand updateRoomCommand) {
        Room room = repository.findById(updateRoomCommand.updateRoomDto().roomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        permissionValidatorService.checkPermission(updateRoomCommand.userEntity(), room, PermissionType.EDIT_ROOM);

        if (updateRoomCommand.updateRoomDto().description() != null) {
            room.setDescription(updateRoomCommand.updateRoomDto().description());
        }

        repository.save(room);
        return null;
    }
}
