package com.projetospring.chatonline.exceptions;

public class UserStatusRoomDontFoundException extends RuntimeException {

    public UserStatusRoomDontFoundException(String message) {
        super(message);
    }
}
