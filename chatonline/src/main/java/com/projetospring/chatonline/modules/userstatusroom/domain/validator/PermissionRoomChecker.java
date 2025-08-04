package com.projetospring.chatonline.modules.userstatusroom.domain.validator;

import com.projetospring.chatonline.core.validators.PermissionCheck;
import com.projetospring.chatonline.modules.userstatusroom.domain.enums.PermissionType;
import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.UserEntity;

public interface PermissionRoomChecker extends PermissionCheck<Room> {

    boolean supports(PermissionType permissionType);

    boolean hasPermission(UserEntity user, Room room);
}
