package com.projetospring.chatonline.modules.userstatusroom.domain.validator;

import com.projetospring.chatonline.core.exceptions.PermissionUserInvalidException;
import com.projetospring.chatonline.modules.userstatusroom.domain.enums.PermissionType;
import com.projetospring.chatonline.modules.room.domain.Room;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionValidatorService {

    private final List<PermissionChecker> checkers;

    @Autowired
    public PermissionValidatorService(List<PermissionChecker> checkers) {
        this.checkers = checkers;
    }


    public boolean hasPermission(UserEntity user, Room room, PermissionType permissionType) {
        return checkers.stream()
                .filter(checker -> checker.supports(permissionType))
                .findFirst()
                .orElseThrow(() -> new PermissionUserInvalidException("User permission don't suport: " + permissionType))
                .hasPermission(user, room);
    }

    public void checkPermission(UserEntity user, Room room, PermissionType permissionType) {
        if (!hasPermission(user, room, permissionType)) {
            throw new PermissionUserInvalidException("User don't have permission: " + permissionType);
        }
    }
}
