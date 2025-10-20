package com.projetospring.chatonline.modules.message.aplication.exceptions;

import com.projetospring.chatonline.core.exceptions.AccessDeniedException;

public class UnauthorizedMessageAccessException extends AccessDeniedException {
    public UnauthorizedMessageAccessException(String message) {
        super(message);
    }
}
