package com.projetospring.chatonline.modules.message.aplication.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EditMessageDto(
        @NotNull UUID messageId,
        @NotBlank String content
) {
}
