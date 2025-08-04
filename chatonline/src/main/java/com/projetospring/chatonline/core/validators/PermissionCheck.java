package com.projetospring.chatonline.core.validators;

import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import com.projetospring.chatonline.modules.userstatusroom.domain.enums.PermissionType;

public interface PermissionCheck<T> {

    boolean supports(PermissionType permissionType);

    boolean hasPermission(UserEntity user, T t);
}
