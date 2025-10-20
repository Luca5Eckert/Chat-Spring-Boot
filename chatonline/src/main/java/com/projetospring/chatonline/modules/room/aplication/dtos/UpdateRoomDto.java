package com.projetospring.chatonline.modules.room.aplication.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateRoomDto(
        @NotNull UUID roomId,
        String description
) {
}
