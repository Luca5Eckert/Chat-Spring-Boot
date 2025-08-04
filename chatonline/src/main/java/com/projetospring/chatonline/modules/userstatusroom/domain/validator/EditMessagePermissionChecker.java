package com.projetospring.chatonline.modules.userstatusroom.domain.validator;

import com.projetospring.chatonline.core.exceptions.PermissionUserInvalidException;
import com.projetospring.chatonline.modules.userstatusroom.domain.enums.PermissionType;
import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import com.projetospring.chatonline.modules.userstatusroom.aplication.repository.UserStatusRoomRepository;
import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoom;
import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoomId;
import org.springframework.beans.factory.annotation.Autowired;

public class EditMessagePermissionChecker implements PermissionRoomChecker{

    private final UserStatusRoomRepository repository;

    public EditMessagePermissionChecker(UserStatusRoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(PermissionType permissionType) {
        return permissionType == PermissionType.EDIT_MESSAGE;
    }

    @Override
    public boolean hasPermission(UserEntity user, Room room) {
        UserStatusRoomId id = new UserStatusRoomId(user, room);
        UserStatusRoom status = repository.findById(id)
                .orElseThrow(() -> new PermissionUserInvalidException("User not found in the room"));
        return status.canEditRoom();
    }
}
