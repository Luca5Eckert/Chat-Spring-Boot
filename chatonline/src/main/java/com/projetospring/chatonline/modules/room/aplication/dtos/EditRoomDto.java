package com.projetospring.chatonline.modules.room.aplication.dtos;

import com.projetospring.chatonline.core.enums.TypeEdit;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record EditRoomDto(TypeEdit typeEdit, @Max(value=20, message="Room name cannot be longer than 20 characters") @NotBlank String nameRoom, @NotBlank String description) {
    
}
