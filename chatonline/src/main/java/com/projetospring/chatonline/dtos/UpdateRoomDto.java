package com.projetospring.chatonline.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record UpdateRoomDto(@Max(value=20, message="Room name cannot be longer than 20 characters") @NotBlank String nameRoom, @NotBlank String description) {
    
}
