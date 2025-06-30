package com.projetospring.chatonline.exceptions;

public class UserDontCanDeleteRoomException extends RuntimeException
{
    public UserDontCanDeleteRoomException(String message) {
        super(message);
    }
}
