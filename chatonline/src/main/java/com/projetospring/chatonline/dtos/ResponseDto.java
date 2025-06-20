package com.projetospring.chatonline.dtos;

import java.time.LocalDateTime;

public record ResponseDto(int statusCode, String message, Object data, LocalDateTime timestamp) {
    public ResponseDto(int statusCode, String message, Object data) {
        this(statusCode, message, data, LocalDateTime.now());
    }

}
