package com.projetospring.chatonline.modules.room.aplication.dtos;

import com.projetospring.chatonline.modules.room.domain.enums.TypeRoom;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record CreateRoomDto(@NotBlank @Max(value=20, message="Room name cannot be longer than 20 characters") String name, @NotBlank TypeRoom type, @NotBlank String description ) {
    
}
