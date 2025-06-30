package com.projetospring.chatonline.exceptions;

public class AccessDeniedEnterRoomException extends AccessDeniedException{

    public AccessDeniedEnterRoomException(String message) {
        super(message);
    }
}
