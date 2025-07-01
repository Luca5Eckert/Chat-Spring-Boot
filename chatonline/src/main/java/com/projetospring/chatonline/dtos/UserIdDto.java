package com.projetospring.chatonline.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserIdDto(@NotBlank UUID id) {
}
