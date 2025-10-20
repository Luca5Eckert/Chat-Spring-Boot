package com.projetospring.chatonline.modules.message.aplication.exceptions;

import com.projetospring.chatonline.core.exceptions.DatabaseEntityNotFoundException;

public class MessageNotFoundException extends DatabaseEntityNotFoundException {
    public MessageNotFoundException(String message) {
        super(message);
    }
}
