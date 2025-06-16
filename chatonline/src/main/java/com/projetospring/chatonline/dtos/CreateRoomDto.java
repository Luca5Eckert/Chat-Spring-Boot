package com.projetospring.chatonline.dtos;

import com.projetospring.chatonline.model.TypeRoom;

import jakarta.validation.constraints.NotBlank;

public record CreateRoomDto(@NotBlank String name, @NotBlank TypeRoom type, @NotBlank String description ) {
    
}
