package com.projetospring.chatonline.modules.room.aplication.dtos;

import com.projetospring.chatonline.modules.room.domain.enums.TypeRoom;

import jakarta.validation.constraints.NotBlank;

public record CreateRoomDto(@NotBlank String name, @NotBlank TypeRoom type, @NotBlank String description ) {
    
}
