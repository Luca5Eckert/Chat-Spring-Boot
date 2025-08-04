package com.projetospring.chatonline.modules.message.domain.validator;

import com.projetospring.chatonline.modules.message.domain.enums.PermissionType;
import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.User;

public interface PermissionChecker {

    boolean supports(PermissionType permissionType);

    boolean hasPermission(User user, Room room);
}
