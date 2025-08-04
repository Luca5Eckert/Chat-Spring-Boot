package com.projetospring.chatonline.modules.userstatusroom.aplication.exceptions;

import com.projetospring.chatonline.core.exceptions.DatabaseEntityNotFoundException;

public class UserStatusRoomNotFoundException extends DatabaseEntityNotFoundException {
    public UserStatusRoomNotFoundException(String message) {
        super(message);
    }
}
