package com.projetospring.chatonline.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record RoomIdDto(@NotBlank UUID id) {

}
