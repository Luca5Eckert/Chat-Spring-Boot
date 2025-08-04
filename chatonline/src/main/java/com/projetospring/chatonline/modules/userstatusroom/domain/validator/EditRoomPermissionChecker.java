package com.projetospring.chatonline.modules.userstatusroom.domain.validator;

import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import com.projetospring.chatonline.modules.userstatusroom.aplication.services.UserStatusRoomService;
import com.projetospring.chatonline.modules.userstatusroom.domain.UserStatusRoom;
import com.projetospring.chatonline.modules.userstatusroom.domain.enums.PermissionType;

public class EditRoomPermissionChecker implements PermissionRoomChecker {

    private final UserStatusRoomService userStatusRoomService;

    public EditRoomPermissionChecker(UserStatusRoomService userStatusRoomService) {
        this.userStatusRoomService = userStatusRoomService;
    }

    @Override
    public boolean supports(PermissionType permissionType) {
        return permissionType == PermissionType.EDIT_ROOM;
    }

    @Override
    public boolean hasPermission(UserEntity user, Room room) {
        UserStatusRoom userStatusRoom = userStatusRoomService.getRelationshipUserWithRoom(user, room);
        return userStatusRoom.canEditRoom();
    }
}
